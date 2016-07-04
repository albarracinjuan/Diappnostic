package edu.uniandes.diappnostic.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;

@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Singleton
public class GestorColaEpisodios{
	private static final Logger log = Logger.getLogger(GestorColaEpisodios.class.getName());
	private static final long TIMEOUT_EMPTY_QUEUE = 5000;
	private Queue<EpisodioDto> cola;
	
	@EJB
	private IEpisodioDAO episodioDAO;
	
	@PostConstruct
	void init(){
		cola = new LinkedList<>();
		registrarEpisodios();
	}
	
	@Asynchronous
	public void registrarEpisodios(){
		while(true){
			if(cola.isEmpty()){
				try {
					wait(TIMEOUT_EMPTY_QUEUE);
				} catch (InterruptedException e) {
					log.log(Level.WARNING, "Error esperando que se agreguen elementos nuevos a la cola de Episodios");
				}
			} else {
				for(EpisodioDto episodio : cola){
					episodioDAO.registrarEpisodio(episodio);
				}
			};
		}
	}
	
	@Lock(LockType.WRITE)
	public void agregarEpisodioACola(EpisodioDto episodioDto){
		cola.add(episodioDto);
	}
}
