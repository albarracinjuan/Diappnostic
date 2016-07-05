package edu.uniandes.diappnostic.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FUNCIONALIDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Funcionalidad.findAll", query="SELECT f FROM Funcionalidad f")
public class Funcionalidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long codigo;

	private String descripcion;

	private String nombre;

	public Funcionalidad() {
	}

	public long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}