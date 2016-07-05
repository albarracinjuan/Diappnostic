package edu.uniandes.diappnostic.dto;

import java.io.Serializable;

public class TokenUsuarioDto implements Serializable {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;	
	
	/**
	 * Constructor con parámetros. 
	 * 
	 * @param token
	 * @param usuario
	 */
	public TokenUsuarioDto(String token) {
		super();
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
