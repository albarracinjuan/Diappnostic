package edu.uniandes.diappnostic.persistencia.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.entities.Episodio;

@Stateless
public class EpisodioDAO implements edu.uniandes.diappnostic.persistencia.IEpisodioDAO {

	/**
	 * 
	 */
	@PersistenceContext(unitName = "DiappnosticWS-ejb")
	private EntityManager em;
	
	/**
	 * registra un episodio en el sistema
	 * @param episodioDto informacion del episodio
	 */
	@Override
	public void registrarEpisodio(EpisodioDto episodioDto)throws IllegalStateException {
		System.out.print("OK: " + episodioDto.getNumDocUsuario());
		
		Query query = em.createNativeQuery("INSERT INTO EPISODIO VALUES (SEQ_EPISODIO.NEXTVAL, to_date(?1, 'dd/mm/yyyy'), ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)");
		query.setParameter(1, episodioDto.getFecha());
		query.setParameter(2, episodioDto.getNivelDolor());
		query.setParameter(3, episodioDto.getPresentaSomnolencia());
		query.setParameter(4, episodioDto.getAlimentosConsumidos());
		query.setParameter(5, episodioDto.getDescripcionVoz());
		query.setParameter(6, episodioDto.getMedicamento());
		query.setParameter(7, episodioDto.getAcividadFisica());
		query.setParameter(8, episodioDto.getLocalizacionDolor());
		query.setParameter(9, episodioDto.getNumDocUsuario());
		query.setParameter(10, episodioDto.getCodRolUsuario());
		
		query.executeUpdate();
		em.flush();
	}
	
	/**
	 * Revisar los episodios de dolor del paciente 
	 * a partir de su no. de identificación.
	 * @param identificacion id del paciente
	 * @return episodio del paciente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EpisodioDto> consultarEpisodios(long identificacion) {
		List<Episodio> lista = new ArrayList<Episodio>();		
		Query query = em.createNamedQuery("Episodio.episodiosUsuario");
		
		query.setParameter("docUsuario", identificacion);
		query.setParameter("codRol", 1);	
		lista = query.getResultList();
								
		return mapper(lista);
	}

	/**
	 * Realiza el mapeo de la consulta al dto
	 * @param episodios
	 * @return
	 */
	private List<EpisodioDto> mapper(List<Episodio> episodios) {
		List<EpisodioDto> epiDtoList = new ArrayList<EpisodioDto>();
		for (Episodio episodio : episodios) {
			EpisodioDto epiDto = new EpisodioDto(
					episodio.getAlimentosConsumidos(), 
					episodio.getDescripcionVoz(), 
					episodio.getFecha().toString(), 
					episodio.getNivelDolor().longValue(), 
					episodio.getPresentaSomnolencia(), 
					episodio.getAcividadFisica().getCodigo(), 
					episodio.getLocalizacionDolor().getCodigo(), 
					episodio.getMedicamento().getCodigo(), 
					episodio.getRolUsuario().getId().getNumDocUsuario(),					
					episodio.getRolUsuario().getId().getCodRol());
			epiDtoList.add(epiDto);
			
		}
		return epiDtoList;

	}
}
