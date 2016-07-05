package edu.uniandes.diappnostic.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class FuncionalidadDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal codigo;
	private String funcionalidad;
	
	/**
	 * Constructor con parámetros.
	 * 
	 * @param codigo
	 * @param funcionalidad
	 */
	public FuncionalidadDto(BigDecimal codigo, String funcionalidad) {
		super();
		this.codigo = codigo;
		this.funcionalidad = funcionalidad;
	}
	
	/**
	 * @return the codigo
	 */
	public BigDecimal getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the funcionalidad
	 */
	public String getFuncionalidad() {
		return funcionalidad;
	}
	/**
	 * @param funcionalidad the funcionalidad to set
	 */
	public void setFuncionalidad(String funcionalidad) {
		this.funcionalidad = funcionalidad;
	}
	
}
