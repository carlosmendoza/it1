package rest;

import java.util.ArrayList;
import java.util.Date;
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
import vos.Oferta;
import vos.Reserva;
import vos.ReservaMasiva;

@Path("reservas")
public class ReservaServices {
	
	@Context
	private ServletContext context;

	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}
	
	@GET
	@Path("unidad/{id}/tipo/{tp}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response analizarOperaciones(@PathParam("id")String unidad, @PathParam("tp")String tipoAloha) {

		TM tm = new TM(getPath());
		ArrayList<Date>[] n;
		try {
			n=tm.analizarOperaciones(unidad, tipoAloha);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(n).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReservas() {
		
		TM tm = new TM(getPath());
		List<Reserva> reservas;
		try {
			reservas = tm.getAllReservas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}
	
	@GET
	@Path("idReserva/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertaPorId(@PathParam("id") String idReserva) {

		TM tm = new TM(getPath());
		Reserva o;
		try {
			if (idReserva == null )
				throw new Exception("Id de la reserva no valido");
			else
			{
			o = tm.darReservaPorId(Integer.parseInt(idReserva));
			}
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(o).build();
	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addReserva(Reserva reserva) {
		TM tm = new TM(getPath());
		try {
			tm.addReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	

		@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response ofertasPocaDemanda() {
		TM tm = new TM(getPath());
		List<Oferta> ofertas;
		try {
			ofertas = tm.ofertasPocaDemanda();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ofertas).build();
	}
	
	@POST
	@Path("reservaMasiva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response addReservaMasiva(ReservaMasiva reservas) {
		TM tm = new TM(getPath());
		try {
			tm.addReservaMasiva(reservas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}
	
	@DELETE
	@Path("idReserva/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response deleteReserva(Reserva reserva) {
		TM tm = new TM(getPath());
		try {
			tm.borrarReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
	@DELETE
	@Path("idReservaMasiva/{id}")	
	
	public Response deleteReservaMasiva(@PathParam("id") int idReserva) {
		TM tm = new TM(getPath());
		try {
			tm.borrarReservaMasiva(idReserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(idReserva).build();
	}
	
	
	
	
	
	

}
