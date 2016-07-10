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
		Thread hiloAlmacenamiento = new Thread(new AlmacenarEpisodiosHilo(episodioDao));
		hiloAlmacenamiento.start();
	}
}
