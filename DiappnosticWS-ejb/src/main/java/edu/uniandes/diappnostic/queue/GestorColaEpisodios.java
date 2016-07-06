package edu.uniandes.diappnostic.queue;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;

@Startup
@Singleton
public class GestorColaEpisodios{
	@EJB
	private IEpisodioDAO episodioDao;
	
	@PostConstruct
	public void init(){
		System.out.println("========= 1. Iniciando Singleton GestorColaEpisodios =========");
		Thread hiloAlmacenamiento = new Thread(new AlmacenarEpisodiosHilo(episodioDao));
		hiloAlmacenamiento.start();
		System.out.println("========= 1. Terminando inicio Singleton GestorColaEpisodios =========");
	}
}
