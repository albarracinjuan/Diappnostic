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

import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jws.*;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.JoseException;

import com.google.gson.Gson;

import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.entities.Usuario;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.persistencia.IEpisodioDAO;
import edu.uniandes.diappnostic.servicios.IGestorUsuario;

/**
 * Implementación de la Interface IGestorUsuario
 * @author JUAN
 *
 */
@Stateless
public class GestorUsuarioImpl implements IGestorUsuario {
	
	/**
	 * Inyección de dependencia con servicio DAO.
	 */
	@Inject
	private IEpisodioDAO episodioDAO;

	/**
	 * Retorna el token con la información del usuario.
	 */
	@Override
	public String autenticarUsuario(long numDoc, String contrasenia) throws DiappnosticException {
		//Se obtiene el usuario.
		UsuarioDto usuario = episodioDAO.obtenerUsuario(numDoc, contrasenia);
		String payload = new Gson().toJson(usuario);
		return crearJWT(payload);
	}
	
	/**
	 * Crea el token de seguridad.
	 * @return
	 */
	private String crearJWT(String payLoad){
		String serializedJwe = null;
		
	     byte[] bytes = {1,2,3};
		 Key key = new AesKey(bytes);
		 JsonWebEncryption jwe = new JsonWebEncryption();
		 jwe.setPayload(payLoad);
		 jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
		 jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
		 jwe.setKey(key);
		try {
		 serializedJwe = jwe.getCompactSerialization();
		} catch (JoseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return serializedJwe;
	}

}
