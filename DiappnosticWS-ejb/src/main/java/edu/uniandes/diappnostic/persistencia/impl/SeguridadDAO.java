package edu.uniandes.diappnostic.persistencia.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.uniandes.diappnostic.dto.FuncionalidadDto;
import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.entities.Usuario;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.persistencia.ISeguridadDAO;

public class SeguridadDAO implements ISeguridadDAO {

	/**
	 * 
	 */
	@PersistenceContext(unitName = "DiappnosticWS-ejb")
	private EntityManager em;
	
	@Override
	public UsuarioDto obtenerUsuario(long numDoc, String contrasenia) throws DiappnosticException {
		Usuario usuario;
		UsuarioDto usuarioDto;
		
		Query query = em.createNamedQuery("Usuario.findById");
		query.setParameter("numDoc", numDoc);
		
		//Se obtiene el usuario.
		try {
			usuario = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new DiappnosticException(401, "El usuario no existe en el sistema.");
		}
		
		//Se verifica que la contrasenia corresponda con la ingresada y se retorna el usuario.
		if (usuario.getContrasena().equals(contrasenia)) {
			//Se obtiene la información de los permisos del rol del usuario. 
			List<FuncionalidadDto> lstPermisos = obtenerPermisos(usuario.getRol().getCodigo());
			
			usuarioDto = new UsuarioDto(usuario.getNumDoc(),
					usuario.getNombres() + " " + usuario.getApellidos(),
					usuario.getRol().getCodigo(), lstPermisos);
			
			return usuarioDto;
		}else{
			throw new DiappnosticException(401, "Contraseña incorrecta.");
		}
	}

	/**
	 * Obtiene los permisos del usuario y arma el dto. 
	 * @param numDoc
	 * @return
	 * @throws DiappnosticException 
	 */
	private List<FuncionalidadDto> obtenerPermisos(long codRol) throws DiappnosticException{
		List<FuncionalidadDto> lstPermisos = new ArrayList<>();
		
		Query query = em.createNativeQuery("select f.codigo, f.nombre from permiso_x_rol pr, funcionalidad f "
				+ "where pr.cod_funcionalidad = f.codigo "
				+ "and pr.cod_rol = ? ");		
		query.setParameter(1, codRol);
		
		//Se obtienen los permisos del rol.
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> resultados =  query.getResultList();
    		for(Object[] tmp : resultados){
    			lstPermisos.add(new FuncionalidadDto((BigDecimal) tmp[0], (String) tmp[1]));
    		}			
		} catch (NoResultException e) {
			throw new DiappnosticException(402, "El usuario no posee permisos sobre ninguna funcionalidad. ");
		}
		
		return lstPermisos;
	}


}
