package edu.uniandes.diappnostic.queue;

import java.util.logging.Level;
import java.util.logging.Logger;
import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;

public class AlmacenarEpisodiosHilo implements Runnable {
	private static Logger log = Logger.getLogger(AlmacenarEpisodiosHilo.class.getName());

	private IEpisodioDAO episodioDAO;

	private ColaEpisodios colaEpisodios;

	public AlmacenarEpisodiosHilo(IEpisodioDAO episodioDAO) {
		this.episodioDAO = episodioDAO;
		colaEpisodios = new ColaEpisodios();
	}

	@Override
	public void run() {
		while (true) {
			if (!colaEpisodios.getCola().isEmpty()) {
				EpisodioDto episodioDto = colaEpisodios.obtenerSiguiente();
				try {
					System.out.println("========= 2.1 Registrando episodio AlmacenarEpisodiosHilo =========");
					episodioDAO.registrarEpisodio(episodioDto);
				} catch (DiappnosticException e) {
					colaEpisodios.agregarEpisodio(episodioDto);
					System.out.println("========= No se pudo insertar el episodio =========");
					log.log(Level.WARNING, "No se pudo insertar el episodio: " + e.getMessage());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						log.log(Level.WARNING, "Error de interrupción de hilo");
					}
				}
			} else {
				try {
					System.out.println("========= 2.3 Cola sin datosd AlmacenarEpisodiosHilo =========");
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					log.log(Level.WARNING, "Error de interrupción de hilo");
				}
			}
		}
	}

}
