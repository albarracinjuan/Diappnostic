package edu.uniandes.diappnostic.persistencia;

import java.util.List;

import javax.ejb.Local;

import edu.uniandes.diappnostic.dto.EpisodioDto;

@Local
public interface IEpisodioDAO {
	/**
	 * registra un episodio en el sistema
	 * @param episodioDto informacion del episodio
	 */
	void registrarEpisodio(EpisodioDto episodioDto)throws IllegalStateException;
	
	/**
	 * Revisar los episodios de dolor del paciente 
	 * a partir de su no. de identificación.
	 * @param identificacion id del paciente
	 * @return episodio del paciente
	 */
	List<EpisodioDto> consultarEpisodios(long identificacion);
}
