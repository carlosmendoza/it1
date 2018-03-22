package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;
import vos.Inmueble;
import vos.Oferta;
@Path("inmuebles")
public class InmuebleServices {
	
	
	@Context
	private ServletContext context;

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getInmuebles() {
		
		TM tm = new TM(getPath());
		List<Inmueble> inmuebles;
		try {
			inmuebles = tm.getAllInmuebles();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(inmuebles).build();
	}
	
	@GET
	@Path("idInmueble/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getInmueblePorId(@PathParam("id") String idInmueble) {

		TM tm = new TM(getPath());
		Inmueble o;
		try {
			if (idInmueble == null )
				throw new Exception("Id del inmueble no valido");
			else
			{
			o = tm.darInmueblePorId(Integer.parseInt(idInmueble));
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(o).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addOferta(Oferta oferta) {
		TM tm = new TM(getPath());
		try {
			tm.addOferta(oferta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(oferta).build();
	}
	
	

}
