package edu.uniandes.diappnostic.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PERMISO_X_ROL database table.
 * 
 */
@Embeddable
public class PermisoXRolPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_FUNCIONALIDAD")
	private long codFuncionalidad;

	@Column(name="COD_ROL")
	private long codRol;

	public PermisoXRolPK() {
	}
	public long getCodFuncionalidad() {
		return this.codFuncionalidad;
	}
	public void setCodFuncionalidad(long codFuncionalidad) {
		this.codFuncionalidad = codFuncionalidad;
	}
	public long getCodRol() {
		return this.codRol;
	}
	public void setCodRol(long codRol) {
		this.codRol = codRol;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisoXRolPK)) {
			return false;
		}
		PermisoXRolPK castOther = (PermisoXRolPK)other;
		return 
			(this.codFuncionalidad == castOther.codFuncionalidad)
			&& (this.codRol == castOther.codRol);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codFuncionalidad ^ (this.codFuncionalidad >>> 32)));
		hash = hash * prime + ((int) (this.codRol ^ (this.codRol >>> 32)));
		
		return hash;
	}
}