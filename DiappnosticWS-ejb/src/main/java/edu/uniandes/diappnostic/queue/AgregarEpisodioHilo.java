package edu.uniandes.diappnostic.queue;

import edu.uniandes.diappnostic.dto.EpisodioDto;

public class AgregarEpisodioHilo implements Runnable {

	private ColaEpisodios colaEpisodios;
	private EpisodioDto episodioDto;
	
	public AgregarEpisodioHilo(EpisodioDto episodioDto){
		this.episodioDto = episodioDto;
		colaEpisodios = new ColaEpisodios();
	}
	
	@Override
	public void run() {
		System.out.println("========= 3. Iniciando Thread AgregarEpisodioHilo =========");
		System.out.println("========= 3.1 Agregando episodio AgregarEpisodioHilo =========");
		colaEpisodios.agregarEpisodio(episodioDto);
		System.out.println("========= 3. Finalizando Thread AgregarEpisodioHilo =========");
	}

}
