package edu.uniandes.diappnostic.persistencia;

import java.util.List;

import javax.ejb.Local;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.entities.Usuario;
import edu.uniandes.diappnostic.exception.DiappnosticException;

@Local
public interface IEpisodioDAO {
	/**
	 * registra un episodio en el sistema
	 * @param episodioDto informacion del episodio
	 */
	void registrarEpisodio(EpisodioDto episodioDto)throws DiappnosticException;
	
	/**
	 * Revisar los episodios de dolor del paciente 
	 * a partir de su no. de identificaciÃ³n.
	 * @param identificacion id del paciente
	 * @return episodio del paciente
	 */
	List<EpisodioDto> consultarEpisodios(long identificacion);
	
	/**
	 * Obtiene el usuario correspondiente al número de documento y contrasenia
	 * ingresados por parámetros.
	 * @param numDoc
	 * @param contrasenia
	 * @return usuario
	 * @throws DiappnosticException
	 */
	public UsuarioDto obtenerUsuario(long numDoc, String contrasenia) throws DiappnosticException;
}
