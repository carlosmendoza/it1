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
import vos.Factura;
import vos.Reserva;

@Path("facturas")
public class FacturaServices {
	

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
	public Response getFacturas() {
		
		TM tm = new TM(getPath());
		List<Factura> facturas;
		try {
			facturas = tm.getAllFacturas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(facturas).build();
	}
	
	@GET
	@Path("idFactura/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertaPorId(@PathParam("id") String idFactura) {

		TM tm = new TM(getPath());
		Reserva o;
		try {
			if (idFactura == null )
				throw new Exception("Id de la factura no valido");
			else
			{
			o = tm.darReservaPorId(Integer.parseInt(idFactura));
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(o).build();
	}
	
	@GET
	@Path("RF1")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getGananciasProveedor() {
		
		TM tm = new TM(getPath());
		List<Factura> facturas;
		try {
			facturas = tm.getAllFacturas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(facturas).build();
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addFactura(Factura factura) {
		TM tm = new TM(getPath());
		try {
			tm.addFactura(factura);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(factura).build();
	}
	

}
