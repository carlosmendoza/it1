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
import vos.Cliente;
import vos.Oferta;


@Path("ofertas")
public class OfertaServices {
	
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
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertas() {
		System.out.println("metodo get ofertas services");
		TM tm = new TM(getPath());
		List<Oferta> ofertas;
		try {
			ofertas = tm.getAllOfertas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ofertas).build();
	}
	
	@GET
	@Path("idOferta/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertaPorId(@PathParam("id") String idOferta) {

		TM tm = new TM(getPath());
		Oferta o;
		try {
			if (idOferta == null )
				throw new Exception("Id de la oferta no valido");
			else
			{
			o = tm.darOfertaPorId(Integer.parseInt(idOferta));
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(o).build();
	}
	
	
	@GET
	@Path("idOperdador/{idO}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertaPorOperador(@PathParam("idO") String idOperador) {

		TM tm = new TM(getPath());
		List<Oferta> ofertas;
		try {
			if (idOperador == null || idOperador.length() == 0)
				throw new Exception("Id del operador no valido");
			else
			{
			ofertas= tm.darOfertasPorOperador(Integer.parseInt(idOperador));
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ofertas).build();
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
