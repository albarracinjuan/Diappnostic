/**
 * 
 */
package edu.uniandes.diappnostic.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 80221940
 *
 */
public class EpisodioDto  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String alimentosConsumidos;
	
	private String descripcionVoz;
			
	private String fecha;
	
	private long nivelDolor;
	
	private String presentaSomnolencia;
	
	private long acividadFisica;
	
	private long localizacionDolor;
		
	private long medicamento;
		
	private long numDocUsuario;
	
	private long codRolUsuario;
	
	private String ipServidor;
	
	/**
	 * @param alimentosConsumidos
	 * @param descripcionVoz
	 * @param fecha
	 * @param nivelDolor
	 * @param presentaSomnolencia
	 * @param acividadFisica
	 * @param localizacionDolor
	 * @param medicamento
	 * @param numDocUsuario
	 * @param codRolUsuario
	 * @param ipServidor
	 */
	public EpisodioDto(String alimentosConsumidos, String descripcionVoz,
			String fecha, long nivelDolor, String presentaSomnolencia,
			long acividadFisica, long localizacionDolor, long medicamento,
			long numDocUsuario, long codRolUsuario, String ipServidor) {
		super();
		this.alimentosConsumidos = alimentosConsumidos;
		this.descripcionVoz = descripcionVoz;
		this.fecha = fecha;
		this.nivelDolor = nivelDolor;
		this.presentaSomnolencia = presentaSomnolencia;
		this.acividadFisica = acividadFisica;
		this.localizacionDolor = localizacionDolor;
		this.medicamento = medicamento;
		this.numDocUsuario = numDocUsuario;
		this.codRolUsuario = codRolUsuario;
		this.ipServidor = ipServidor;
	}

	/**
	 * @return the alimentosConsumidos
	 */
	public String getAlimentosConsumidos() {
		return alimentosConsumidos;
	}

	/**
	 * @param alimentosConsumidos the alimentosConsumidos to set
	 */
	public void setAlimentosConsumidos(String alimentosConsumidos) {
		this.alimentosConsumidos = alimentosConsumidos;
	}

	/**
	 * @return the descripcionVoz
	 */
	public String getDescripcionVoz() {
		return descripcionVoz;
	}

	/**
	 * @param descripcionVoz the descripcionVoz to set
	 */
	public void setDescripcionVoz(String descripcionVoz) {
		this.descripcionVoz = descripcionVoz;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the nivelDolor
	 */
	public long getNivelDolor() {
		return nivelDolor;
	}

	/**
	 * @param nivelDolor the nivelDolor to set
	 */
	public void setNivelDolor(long nivelDolor) {
		this.nivelDolor = nivelDolor;
	}

	/**
	 * @return the presentaSomnolencia
	 */
	public String getPresentaSomnolencia() {
		return presentaSomnolencia;
	}

	/**
	 * @param presentaSomnolencia the presentaSomnolencia to set
	 */
	public void setPresentaSomnolencia(String presentaSomnolencia) {
		this.presentaSomnolencia = presentaSomnolencia;
	}

	/**
	 * @return the acividadFisica
	 */
	public long getAcividadFisica() {
		return acividadFisica;
	}

	/**
	 * @param acividadFisica the acividadFisica to set
	 */
	public void setAcividadFisica(long acividadFisica) {
		this.acividadFisica = acividadFisica;
	}

	/**
	 * @return the localizacionDolor
	 */
	public long getLocalizacionDolor() {
		return localizacionDolor;
	}

	/**
	 * @param localizacionDolor the localizacionDolor to set
	 */
	public void setLocalizacionDolor(long localizacionDolor) {
		this.localizacionDolor = localizacionDolor;
	}

	/**
	 * @return the medicamento
	 */
	public long getMedicamento() {
		return medicamento;
	}

	/**
	 * @param medicamento the medicamento to set
	 */
	public void setMedicamento(long medicamento) {
		this.medicamento = medicamento;
	}

	/**
	 * @return the numDocUsuario
	 */
	public long getNumDocUsuario() {
		return numDocUsuario;
	}

	/**
	 * @param numDocUsuario the numDocUsuario to set
	 */
	public void setNumDocUsuario(long numDocUsuario) {
		this.numDocUsuario = numDocUsuario;
	}

	/**
	 * @return the codRolUsuario
	 */
	public long getCodRolUsuario() {
		return codRolUsuario;
	}

	/**
	 * @param codRolUsuario the codRolUsuario to set
	 */
	public void setCodRolUsuario(long codRolUsuario) {
		this.codRolUsuario = codRolUsuario;
	}

	/**
	 * @return the ipServidor
	 */
	public String getIpServidor() {
		return ipServidor;
	}

	/**
	 * @param ipServidor the ipServidor to set
	 */
	public void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}
	
}
