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
		
		return Response.status(200).entity(episodio.getNumDocUsuario()).build();
		
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
