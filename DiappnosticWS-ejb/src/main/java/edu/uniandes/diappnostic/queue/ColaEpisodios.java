package edu.uniandes.diappnostic.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import edu.uniandes.diappnostic.dto.EpisodioDto;

public class ColaEpisodios {
	private static Queue<EpisodioDto> cola = new LinkedList<>();
	private final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();
	
	public void agregarEpisodio(EpisodioDto episodioDto){
		try{
			rrwl.writeLock().lock();
			cola.add(episodioDto);
		}finally{
			rrwl.writeLock().unlock();
		}
	}
	
	public EpisodioDto obtenerSiguiente(){
		try{
			rrwl.readLock().lock();
			return cola.poll();
		}finally{
			rrwl.readLock().unlock();
		}
		
	}
	
	public void remover(){
		try{
			rrwl.writeLock().lock();
			cola.remove();
		}finally{
			rrwl.writeLock().unlock();
		}
	}

	/**
	 * @return the cola
	 */
	public Queue<EpisodioDto> getCola() {
		try{
			rrwl.readLock().lock();
			return ColaEpisodios.cola;
		}finally{
			rrwl.readLock().unlock();
		}
	}

	/**
	 * @param cola the cola to set
	 */
	public void setCola(Queue<EpisodioDto> cola) {
		try{
			rrwl.writeLock().lock();
			ColaEpisodios.cola = cola;
		}finally{
			rrwl.writeLock().unlock();
		}
	}
	
	
}
