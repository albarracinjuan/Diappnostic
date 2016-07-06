package edu.uniandes.diappnostic.queue;

import java.util.logging.Level;
import java.util.logging.Logger;
import edu.uniandes.diappnostic.dto.EpisodioDto;
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
		System.out.println("========= 2. Iniciando Thread AlmacenarEpisodiosHilo =========");
		while (true) {
			if (!colaEpisodios.getCola().isEmpty()) {
				EpisodioDto episodioDto = colaEpisodios.obtenerSiguiente();
				try {
					System.out.println("========= 2.1 Registrando episodio AlmacenarEpisodiosHilo =========");
					episodioDAO.registrarEpisodio(episodioDto);
				} catch (Exception e) {
					colaEpisodios.agregarEpisodio(episodioDto);
					log.log(Level.WARNING, "Conexión perdida con la BD: " + e.getMessage());
					try {
						System.out.println("========= 2.2 Conexion perdida con la base de datos =========");
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
