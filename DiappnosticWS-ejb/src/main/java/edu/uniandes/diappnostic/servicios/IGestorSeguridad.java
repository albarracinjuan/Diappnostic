package edu.uniandes.diappnostic.servicios;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import edu.uniandes.diappnostic.dto.FuncionalidadDto;
import edu.uniandes.diappnostic.exception.DiappnosticException;

/**
 * Gestiona la autenticación y autorización de usuarios.
 * 
 * @author JUAN
 *
 */
@Local
public interface IGestorSeguridad {

	/**
	 * Autentica al usuario y devuelve el token de seguridad. 
	 * 
	 * @param numDoc
	 * @param contrasenia
	 * @return
	 * @throws DiappnosticException
	 */
	String autenticarUsuario(long numDoc, String contrasenia) throws DiappnosticException;

	/**
	 * Desencripta el token enviado en el request. 
	 * 
	 * @param token
	 * @return
	 * @throws DiappnosticException
	 */
	String decodificarToken(String token) throws DiappnosticException;
	
	/**
	 * Crea el token de seguridad.
	 * 
	 * @param payLoad
	 * @return
	 */
	public String crearJWT(String payLoad);

	/**
	 * Verifica que el usuario tenga permisos sobre la funcionalidad a la que desea acceder. 
	 * 
	 * @param codFuncionalidad
	 * @param lstPermisos
	 * @return
	 * @throws DiappnosticException
	 */
	boolean verificarPermisos(BigDecimal codFuncionalidad, List<FuncionalidadDto> lstPermisos) ;
}
