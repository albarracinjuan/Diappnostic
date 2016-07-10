package edu.uniandes.diappnostic.serviciosImpl;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;

import edu.uniandes.diappnostic.dto.FuncionalidadDto;
import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.persistencia.ISeguridadDAO;
import edu.uniandes.diappnostic.servicios.IGestorSeguridad;

/**
 * Implementación de la Interface IGestorUsuario
 * @author JUAN
 *
 */
@Stateless
public class GestorSeguridadImpl implements IGestorSeguridad {
	
	/**
	 * Inyección de dependencia con servicio DAO.
	 */
	@Inject
	private ISeguridadDAO seguridadDAO;
	

	@Override
	public String autenticarUsuario(long numDoc, String contrasenia) throws DiappnosticException {
		//Se obtiene el usuario.
		UsuarioDto usuario = seguridadDAO.obtenerUsuario(numDoc, contrasenia);
		String payload = new Gson().toJson(usuario);
		return crearJWT(payload);
	}
	
	public String crearJWT(String payLoad){
		// Create an HMAC-protected JWS object with some payload
		JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
		                                    new Payload(payLoad));

		// We need a 256-bit key for HS256 which must be pre-shared
		byte[] sharedKey = new byte[32];
		new SecureRandom().nextBytes(sharedKey);

		// Apply the HMAC to the JWS object
		try {
			jwsObject.sign(new MACSigner(sharedKey));
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String token = jwsObject.serialize();

		return token;
	}

	@Override
	public String decodificarToken(String token) throws DiappnosticException {
		JWSObject jwsObject;
		
		//Se decodifica el token. 
		try {
		    jwsObject = JWSObject.parse(token);
		} catch (ParseException e) {
			throw new DiappnosticException(402, "Token de seguridad inválido: " + e.getMessage());
		}
		
		return jwsObject.getPayload().toString();
	}
	
	
	@Override
	public boolean verificarPermisos(BigDecimal codFuncionalidad, List<FuncionalidadDto> lstPermisos){
		boolean tienePermisos = false;
		
		//Se recorre la lista de permisos para verificar que tnega permisos sobre la funcionalidad especificada.
		for (FuncionalidadDto funcionalidadDto : lstPermisos) {
			if (funcionalidadDto.getCodigo().compareTo(codFuncionalidad) == 0) {
				tienePermisos = true;
				break;
			}
		}
		return tienePermisos;
	}

}
