/**
 * 
 */
package edu.uniandes.diappnostic.servicios;

import java.util.List;

import javax.ejb.Local;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.entities.Episodio;
import edu.uniandes.diappnostic.entities.Usuario;
import edu.uniandes.diappnostic.exception.DiappnosticException;



/**
 * @author 80221940
 *
 */
@Local
public interface IServicioGestor {
	
	/**
	 * registra un episodio en el sistema
	 * @param episodio informacion del episodio
	 */
	void registrarEpisodio(EpisodioDto episodio);
	
	/**
	 * Revisar los episodios de dolor del paciente 
	 * a partir de su no. de identificación.
	 * @param identificacion id del paciente
	 * @return episodio del paciente
	 */
	List<EpisodioDto> consultarEpisodios(long identificacion);
	
	Usuario obtenerUsuario(long numDoc, String contrasenia) throws DiappnosticException;

}
