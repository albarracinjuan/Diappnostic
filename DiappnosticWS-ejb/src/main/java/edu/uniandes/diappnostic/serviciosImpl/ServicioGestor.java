package edu.uniandes.diappnostic.serviciosImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;
import edu.uniandes.diappnostic.queue.AgregarEpisodioHilo;
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
	@EJB
	private IEpisodioDAO episodioDao;
	
	private static Logger log = Logger.getLogger(ServicioGestor.class.getName());
	/**
	 * registra un episodio en el sistema
	 * @param episodio informacion del episodio
	 */
	@Override
	public void registrarEpisodio(EpisodioDto episodioDto) {
		System.out.println("========= 3. Registrando episodio inicio =========");
		Thread agregarEpisodioHilo = new Thread(new AgregarEpisodioHilo(episodioDto));
		agregarEpisodioHilo.start();
		try {
			agregarEpisodioHilo.join();
		} catch (InterruptedException e) {
			log.log(Level.WARNING, e.getMessage());
		}
		System.out.println("========= 3. Registrando episodio fin =========");
		
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
