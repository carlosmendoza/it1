package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tm.TM;
import vos.Estandar;
import vos.IndiceOcupacion;
import vos.Oferta;
import vos.OfertaTotal;


@Path("iter")
public class Iter3Services {
	
	@Context
	private ServletContext context;

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	
	
	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	// Se obtienen todas las ofertas de todos operadores
	@GET
	@Path("RFC10/{caracteristicas}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getRF10(@PathParam("caracteristicas") String caracteristicas) {
		TM tm = new TM(getPath());
		List<Estandar> ofertas;
		try {
			ofertas = tm.getRFC10(caracteristicas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println(caracteristicas);
		return Response.status(200).entity(ofertas).build();
		
		
	}

	
	

}

