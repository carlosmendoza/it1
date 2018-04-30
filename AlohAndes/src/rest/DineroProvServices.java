package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.TM;
import vos.DineroProv;
import vos.Oferta;

@Path("dineroProv")
public class DineroProvServices {
	@Context
	private ServletContext context;

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	
	@GET
	@Path("fecha/{fecha}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getDineroProv(@PathParam ("fecha") String fecha) {
		TM tm = new TM(getPath());
		List<DineroProv> dinero;
		try {
			dinero = tm.getDineroR(fecha);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(dinero).build();
	}
	
	

}
