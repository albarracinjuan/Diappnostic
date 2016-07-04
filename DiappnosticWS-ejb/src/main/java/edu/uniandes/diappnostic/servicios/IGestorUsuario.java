package edu.uniandes.diappnostic.servicios;

import javax.ejb.Local;

import edu.uniandes.diappnostic.exception.DiappnosticException;

/**
 * Gestiona la autenticación y autorización de usuarios.
 * @author JUAN
 *
 */
@Local
public interface IGestorUsuario {
	
	String autenticarUsuario(long numDoc, String contrasenia) throws DiappnosticException;
}
