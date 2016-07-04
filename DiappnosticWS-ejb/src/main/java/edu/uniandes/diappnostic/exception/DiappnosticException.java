package edu.uniandes.diappnostic.exception;

/**
 * Clase que representa una excepción de negocio.
 * @author JUAN
 *
 */
public class DiappnosticException extends Exception {
	
	/** Serial de la clase */
	private static final long serialVersionUID = 1L;
	
	/** Código del error */
	private int codError;
	
	/** Mensaje Asociado */
	private String mensaje;
	
	/**
	 * Constructor con parámetros de la clase.
	 * 
	 * @param codError
	 * @param mensaje
	 */
	public DiappnosticException(int codError, String mensaje) {
		super();
		this.codError = codError;
		this.mensaje = mensaje;
	}

	/**
	 * @return the codError
	 */
	public int getCodError() {
		return codError;
	}

	/**
	 * @param codError the codError to set
	 */
	public void setCodError(int codError) {
		this.codError = codError;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}

