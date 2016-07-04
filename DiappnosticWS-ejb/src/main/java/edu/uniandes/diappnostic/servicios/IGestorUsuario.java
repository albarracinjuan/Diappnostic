package edu.uniandes.diappnostic.servicios;

import javax.ejb.Local;

import edu.uniandes.diappnostic.exception.DiappnosticException;

/**
 * Gestiona la autenticaci�n y autorizaci�n de usuarios.
 * @author JUAN
 *
 */
@Local
public interface IGestorUsuario {
	
	String autenticarUsuario(long numDoc, String contrasenia) throws DiappnosticException;
}
