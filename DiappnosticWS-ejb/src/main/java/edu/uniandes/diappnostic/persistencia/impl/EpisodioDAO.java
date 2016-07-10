package edu.uniandes.diappnostic.persistencia.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.dto.FuncionalidadDto;
import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.entities.Episodio;
import edu.uniandes.diappnostic.entities.PermisoXRol;
import edu.uniandes.diappnostic.entities.Usuario;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;

@Stateless
public class EpisodioDAO implements IEpisodioDAO {

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
	public void registrarEpisodio(EpisodioDto episodioDto)throws DiappnosticException {
		System.out.print("OK: " + episodioDto.getNumDocPaciente());
		
		Query query = em.createNativeQuery("INSERT INTO EPISODIO VALUES (SEQ_EPISODIO.NEXTVAL, to_date(?1, 'dd/mm/yyyy'), ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)");
		query.setParameter(1, episodioDto.getFecha());
		query.setParameter(2, episodioDto.getNivelDolor());
		query.setParameter(3, episodioDto.getPresentaSomnolencia());
		query.setParameter(4, episodioDto.getAlimentosConsumidos());
		query.setParameter(5, episodioDto.getDescripcionVoz());
		query.setParameter(6, episodioDto.getMedicamento());
		query.setParameter(7, episodioDto.getAcividadFisica());
		query.setParameter(8, episodioDto.getLocalizacionDolor());
		query.setParameter(9, episodioDto.getNumDocPaciente());
		query.setParameter(10, episodioDto.getIpServidor());
		
		int ret = query.executeUpdate();
		em.flush();
		
		if(ret == 0){
			System.out.println("No se pudo insertar el episodio "+ret);
			throw new DiappnosticException(1, "No se pudo insertar el episodio");
		}
	}
	
	/**
	 * Revisar los episodios de dolor del paciente 
	 * a partir de su no. de identificaciÃ³n.
	 * @param identificacion id del paciente
	 * @return episodio del paciente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EpisodioDto> consultarEpisodios(long identificacion) {
		List<Episodio> lista = new ArrayList<Episodio>();		
		Query query = em.createNamedQuery("Episodio.episodiosUsuario");
		
		query.setParameter("docUsuario", identificacion);
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
					episodio.getAcividadFisica() != null ? episodio.getAcividadFisica().getCodigo() : null, 
					episodio.getLocalizacionDolor() != null ? episodio.getLocalizacionDolor().getCodigo() : null, 
					episodio.getMedicamento() != null ? episodio.getMedicamento().getCodigo() : null, 
					episodio.getUsuario().getNumDoc(),					
					episodio.getIpServidor(),
					null);
			epiDtoList.add(epiDto);
			
		}
		return epiDtoList;

	}

	@Override
	public UsuarioDto obtenerUsuario(long numDoc, String contrasenia) throws DiappnosticException {
		Usuario usuario;
		UsuarioDto usuarioDto;
		
		Query query = em.createNamedQuery("Usuario.findById");
		query.setParameter("numDoc", numDoc);
		
		//Se obtiene el usuario.
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new DiappnosticException(401, "El usuario no existe en el sistema.");
		}
		
		//Se verifica que la contrasenia corresponda con la ingresada y se retorna el usuario.
		if (usuario.getContrasena().equals(contrasenia)) {
			//Se obtiene la información de los permisos del rol del usuario. 
			List<FuncionalidadDto> lstPermisos = obtenerPermisos(usuario.getRol().getCodigo());
			
			usuarioDto = new UsuarioDto(usuario.getNumDoc(),
					usuario.getNombres() + " " + usuario.getApellidos(),
					usuario.getRol().getCodigo(), lstPermisos);
			
			return usuarioDto;
		}else{
			throw new DiappnosticException(401, "Contraseña incorrecta.");
		}
	}

	/**
	 * Obtiene los permisos del usuario y arma el dto. 
	 * @param numDoc
	 * @return
	 * @throws DiappnosticException 
	 */
	private List<FuncionalidadDto> obtenerPermisos(long codRol) throws DiappnosticException{
		List<FuncionalidadDto> lstPermisos = new ArrayList<>();
		
		Query query = em.createNativeQuery("select f.codigo, f.nombre from permiso_x_rol pr, funcionalidad f "
				+ "where pr.cod_funcionalidad = f.codigo "
				+ "and pr.cod_rol = ? ");		
		query.setParameter(1, codRol);
		
		//Se obtienen los permisos del rol.
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> resultados =  query.getResultList();
    		for(Object[] tmp : resultados){
    			lstPermisos.add(new FuncionalidadDto((BigDecimal) tmp[0], (String) tmp[1]));
    		}			
		} catch (NoResultException e) {
			throw new DiappnosticException(402, "El usuario no posee permisos sobre ninguna funcionalidad. ");
		}
		
		return lstPermisos;
	}
}
