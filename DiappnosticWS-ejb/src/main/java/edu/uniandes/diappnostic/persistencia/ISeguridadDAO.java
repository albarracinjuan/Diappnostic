package edu.uniandes.diappnostic.persistencia;

import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.exception.DiappnosticException;

public interface ISeguridadDAO {

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
