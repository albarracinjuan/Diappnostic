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
import edu.uniandes.diappnostic.entities.Episodio;
import edu.uniandes.diappnostic.exception.DiappnosticException;
import edu.uniandes.diappnostic.servicios.IGestorUsuario;
import edu.uniandes.diappnostic.servicios.IServicioGestor;

@Path("/servicios")
@Stateless
public class DiappnosticRS {
	
	/**
	 * 
	 */
	@EJB
	private IServicioGestor servicioGestor;
	
	@Inject
	private IGestorUsuario gestorUsuario;

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
		Gson gsonBuilder = new GsonBuilder().create();
		EpisodioDto episodio = gsonBuilder.fromJson(jsonRequest, EpisodioDto.class);
		
		servicioGestor.registrarEpisodio(episodio);
		
		return Response.status(200).entity(episodio.getNumDocPaciente()).build();
		
	}
	
	/**
	 * Servicio que se encarga de registrar los epidodios de dolor
	 * @param episodio
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("autenticar/")
	public Response autenticarUsuario(String jsonRequest) {
//		Gson gsonBuilder = new GsonBuilder().create();
//		LoginDto loginDto = gsonBuilder.fromJson(jsonRequest, LoginDto.class);
//		
//		try {
//			String jwt = gestorUsuario.autenticarUsuario(loginDto.getUsuario(), loginDto.getContrasenia());
//			TokenUsuarioDto token = new TokenUsuarioDto(jwt);
//			String response = new Gson().toJson(token);
//			return Response.status(200).entity(response).build();
//		} catch (DiappnosticException e) {
//			return Response.status(e.getCodError()).entity(e).build();
//		}
		String dummy = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c3VhcmlvIjoiMTA1MjM5Mjc1NSIsIm5vbWJyZSI6Ikp1YW4gQ2FybG9zIEFsYmFycmFjw61uIiwicm9sIjoyLCJwZXJtaXNvcyI6W3siY29kaWdvIjoiMSIsIm5vbWJyZUZ1bmNpb25hbGlkYWQiOiJSZWdpc3RybyBlcGlzb2RpbyJ9LHsiY29kaWdvIjoiMiIsIm5vbWJyZUZ1bmNpb25hbGlkYWQiOiJDb25zdWx0YSBlcGlzb2Rpb3MgcHJvcGlvcyJ9LHsiY29kaWdvIjoiMyIsIm5"
				+ "vbWJyZUZ1bmNpb25hbGlkYWQiOiJDb25zdWx0YSBlcGlzb2Rpb3MgcGFjaWVudGVzIn1dfQ.lduh_mwKlDRuKOQnTqP0NwmBJLiACFFprdvze3hDUns";
		return Response.status(200).entity(dummy).build();
		
	}
	
	/**
	 * Servicio que se encarga de realizar la consulta de episodios 
	 * a partir de la identificacion del usuario
	 * @param id
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("consultar/{id}")
	public Response consultarEpisodio(@PathParam("id") long id) {		
		List<EpisodioDto> listaEpisodios = servicioGestor.consultarEpisodios(id);
						
		String response = new Gson().toJson(listaEpisodios);
		return Response.status(200).entity(response).build();		
	}
	
	
    
}
