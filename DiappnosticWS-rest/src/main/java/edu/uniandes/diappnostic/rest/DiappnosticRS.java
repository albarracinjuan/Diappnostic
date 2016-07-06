package edu.uniandes.diappnostic.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.uniandes.diappnostic.dto.EpisodioDto;
import edu.uniandes.diappnostic.dto.LoginDto;
import edu.uniandes.diappnostic.dto.TokenUsuarioDto;
import edu.uniandes.diappnostic.dto.UsuarioDto;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.servicios.IGestorSeguridad;
import edu.uniandes.diappnostic.servicios.IServicioGestor;
import edu.uniandes.diappnostic.util.ConstantesApp;

@Path("/servicios")
@Stateless
public class DiappnosticRS {
	
	/**
	 * 
	 */
	@EJB
	private IServicioGestor servicioGestor;
	
	@Inject
	private IGestorSeguridad gestorSeguridad;

	@GET
	@Path("estado/")
	public Response consultarEstadoServicio(){
		String estado = "Servicio en funcionamiento.";
		return Response.status(200).entity(estado).build();
	}
	
	/**
	 * Servicio que se encarga de registrar los epidodios de dolor
	 * @param episodio
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("registrar/")
	public Response registrarEpisodio(String jsonRequest) {

		try {
			jsonRequest = gestorSeguridad.decodificarToken(jsonRequest);	
		} catch (DiappnosticException e) {
			return Response.status(e.getCodError()).entity(e.getMensaje()).build();
		}
		
		//Se mapea la información obtenida del token
		Gson gsonBuilder = new GsonBuilder().create();
		EpisodioDto episodio = gsonBuilder.fromJson(jsonRequest, EpisodioDto.class);
		if (episodio.getDatosUsuario() == null) {
			return Response.status(401).entity("Usuario no identificado.").build();	
		}
		
		//Se verifica que el usuario tenga permisos para acceder a la funcionalidad requerida.
		if (gestorSeguridad.verificarPermisos(ConstantesApp.FUNCIONALIDAD_REGISTRO, episodio.getDatosUsuario().getPermisos())) {
			//Se registra el episodio. 
			servicioGestor.registrarEpisodio(episodio);
			
			return Response.status(200).entity(episodio.getNumDocPaciente()).build();			
		}else{
			return Response.status(401).entity("El usuario no tiene permisos para registrar episodios.").build();	
		}
	}
	
	/**
	 * Servicio que se encarga de registrar los epidodios de dolor
	 * @param episodio
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("autenticar/")
	public Response autenticarUsuario(String jsonRequest) {
		Gson gsonBuilder = new GsonBuilder().create();
		LoginDto loginDto = gsonBuilder.fromJson(jsonRequest, LoginDto.class);
		
		try {
			String jwt = gestorSeguridad.autenticarUsuario(loginDto.getUsuario(), loginDto.getContrasenia());
			TokenUsuarioDto token = new TokenUsuarioDto(jwt);
			String response = new Gson().toJson(token);
			return Response.status(200).entity(response).build();
		} catch (DiappnosticException e) {
			return Response.status(e.getCodError()).entity(e.getMensaje()).build();
		}
	}
	
	/**
	 * Servicio que se encarga de realizar la consulta de episodios 
	 * a partir de la identificacion del usuario
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("consultar/{id}/{token}")
	public Response consultarEpisodio(@PathParam("id") long id, @PathParam("token") String token) {
		//Se decodifica el token
		String jsonRequest = null;
		try {
			jsonRequest = gestorSeguridad.decodificarToken(token);	
		} catch (DiappnosticException e) {
			return Response.status(e.getCodError()).entity(e.getMensaje()).build();
		}
		Gson gsonBuilder = new GsonBuilder().create();
		UsuarioDto usuarioDto = gsonBuilder.fromJson(jsonRequest, UsuarioDto.class);
		if (usuarioDto.getPermisos() == null) {
			return Response.status(401).entity("El usuario no posee ningún permiso.").build();	
		}
		//Se verifica que el usuario tenga permisos para acceder a la funcionalidad requerida.
		if (gestorSeguridad.verificarPermisos(ConstantesApp.FUNCIONALIDAD_CONSULTA_PACIENTES, usuarioDto.getPermisos())) {		
			List<EpisodioDto> listaEpisodios = servicioGestor.consultarEpisodios(id);
			
			String response = gestorSeguridad.crearJWT(new Gson().toJson(listaEpisodios));
			return Response.status(200).entity(response).build();		
		}else{
			return Response.status(401).entity("El usuario no tiene permisos para consultar episodios.").build();	
		}
		
	}
	
	
    
}
