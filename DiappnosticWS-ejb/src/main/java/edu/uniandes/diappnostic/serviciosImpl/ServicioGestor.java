package edu.uniandes.diappnostic.serviciosImpl;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;
import edu.uniandes.diappnostic.servicios.IServicioGestor;

/**
 * @author 80221940
 *
 */
@Stateless
public class ServicioGestor implements IServicioGestor {
	
	/**
	 * 
	 */
	@Inject
	private IEpisodioDAO episodioDao;
	

	/**
	 * registra un episodio en el sistema
	 * @param episodio informacion del episodio
	 */
	@Override
	public void registrarEpisodio(EpisodioDto episodioDto) {
		
		episodioDao.registrarEpisodio(episodioDto);
	}

	/**
	 * Revisar los episodios de dolor del paciente 
	 * a partir de su no. de identificación.
	 * @param identificacion id del paciente
	 * @return episodio del paciente
	 */
	@Override
	public List<EpisodioDto> consultarEpisodios(long identificacion) {
		return episodioDao.consultarEpisodios(identificacion);
	}
}
