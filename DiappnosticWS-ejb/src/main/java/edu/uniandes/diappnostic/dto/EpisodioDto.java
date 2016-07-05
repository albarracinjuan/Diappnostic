/**
 * 
 */
package edu.uniandes.diappnostic.dto;

import java.io.Serializable;

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
	
	private Long acividadFisica;
	
	private Long localizacionDolor;
		
	private Long medicamento;
		
	private long numDocPaciente;
	
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
			Long acividadFisica, Long localizacionDolor, Long medicamento,
			long numDocUsuario, String ipServidor) {
		super();
		this.alimentosConsumidos = alimentosConsumidos;
		this.descripcionVoz = descripcionVoz;
		this.fecha = fecha;
		this.nivelDolor = nivelDolor;
		this.presentaSomnolencia = presentaSomnolencia;
		this.acividadFisica = acividadFisica;
		this.localizacionDolor = localizacionDolor;
		this.medicamento = medicamento;
		this.numDocPaciente = numDocUsuario;
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
	public Long getAcividadFisica() {
		return acividadFisica;
	}

	/**
	 * @param acividadFisica the acividadFisica to set
	 */
	public void setAcividadFisica(Long acividadFisica) {
		this.acividadFisica = acividadFisica;
	}

	/**
	 * @return the localizacionDolor
	 */
	public Long getLocalizacionDolor() {
		return localizacionDolor;
	}

	/**
	 * @param localizacionDolor the localizacionDolor to set
	 */
	public void setLocalizacionDolor(Long localizacionDolor) {
		this.localizacionDolor = localizacionDolor;
	}

	/**
	 * @return the medicamento
	 */
	public Long getMedicamento() {
		return medicamento;
	}

	/**
	 * @param medicamento the medicamento to set
	 */
	public void setMedicamento(Long medicamento) {
		this.medicamento = medicamento;
	}

	/**
	 * @return the numDocPaciente
	 */
	public long getNumDocPaciente() {
		return numDocPaciente;
	}

	/**
	 * @param numDocPaciente the numDocPaciente to set
	 */
	public void setNumDocPaciente(long numDocPaciente) {
		this.numDocPaciente = numDocPaciente;
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
