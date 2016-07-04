package edu.uniandes.diappnostic.serviciosImpl;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;

import edu.uniandes.diappnostic.entities.Usuario;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.servicios.IGestorUsuario;
import edu.uniandes.diappnostic.servicios.IServicioGestor;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Implementación de la Interface IGestorUsuario
 * @author JUAN
 *
 */
@Stateless
public class GestorUsuarioImpl implements IGestorUsuario {
	
	/** Inyección de dependencia del getor de servicio*/
	@Inject
	private IServicioGestor servicioGestor;
	

	/**
	 * Retorna el token con la información del usuario.
	 */
	@Override
	public String autenticarUsuario(long numDoc, String contrasenia) throws DiappnosticException {
		//Se obtiene el usuario.
		Usuario usuario = servicioGestor.obtenerUsuario(numDoc, contrasenia);		
		return crearJWT("Millenials", "Millenials", "Usuario: " +  usuario.getNumDoc(), 26751765L);
	}
	
	/**
	 * Crea el token de seguridad.
	 * @return
	 */
	private String crearJWT(String id, String issuer, String subject, long ttlMillis){
		// create new key
		SecretKey secretKey = null;
		try {
		secretKey = KeyGenerator.getInstance("AES").generateKey();
		} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
		}
		// get base64 encoded version of the key
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		 
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(encodedKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		 
		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);
		 
		//if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		 
		 //Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

}
