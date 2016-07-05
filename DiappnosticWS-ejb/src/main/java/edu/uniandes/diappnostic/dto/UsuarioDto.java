package edu.uniandes.diappnostic.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long numDoc;
	private String nombre;
	private long rol;
	private List<FuncionalidadDto> permisos;
	
	/**
	 * Constructor con parámetros. 
	 * 
	 * @param numDoc
	 * @param nombre
	 * @param rol
	 * @param permisos
	 */
	public UsuarioDto(long numDoc, String nombre, long rol, List<FuncionalidadDto> permisos) {
		super();
		this.numDoc = numDoc;
		this.nombre = nombre;
		this.rol = rol;
		this.permisos = permisos;
	}

	/**
	 * @return the numDoc
	 */
	public long getNumDoc() {
		return numDoc;
	}

	/**
	 * @param numDoc the numDoc to set
	 */
	public void setNumDoc(long numDoc) {
		this.numDoc = numDoc;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the rol
	 */
	public long getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(long rol) {
		this.rol = rol;
	}

	/**
	 * @return the permisos
	 */
	public List<FuncionalidadDto> getPermisos() {
		return permisos;
	}

	/**
	 * @param permisos the permisos to set
	 */
	public void setPermisos(List<FuncionalidadDto> permisos) {
		this.permisos = permisos;
	}
}
