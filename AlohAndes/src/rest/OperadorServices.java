package rest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DAOOperador;
import tm.TM;
import vos.OfertaRFC12;
import vos.Operador;
import vos.OperadorRFC12;
@Path("operadores")
public class OperadorServices {
	@Context
	private ServletContext context;

	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el
	 * deploy actual dentro del servidor.
	 * 
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}

	/**
	 * Metodo que expone servicio REST usando GET que da todos los operadores de la base de datos. 
	 * <b>URL: </b> http://"ip o nombre de host":8080/AlohAndes/rest/(...)
	 * @return Json con todos los operadores de la base de datos o json con el
	 * error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOperadores() {
		TM tm = new TM(getPath());
		List<Operador> operadores;
		try {
			operadores = tm.getAllOperadores();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(operadores).build();
	}



	/**
	 * Metodo que expone servicio REST usando POST que agrega el operador que
	 * recibe en Json <b>URL: </b> http://"ip o nombre de
	 * host":8080/AlohAndes/rest/(...)
	 * 
	 * @param cliente
	 *            - operador a agregar
	 * @return Json con el operador que agrego o Json con el error que se
	 *         produjo
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addOperador(Operador operador) {
		TM tm = new TM(getPath());
		try {
			tm.addOperador(operador);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(operador).build();
	}

	
	/**
	 * Metodo que expone servicio REST usando PUT que actualiza el operador que
	 * recibe en Json <b>URL: </b> http://"ip o nombre de
	 * host":8080/AlohAndes/rest/operadores
	 * 
	 * @param cliente
	 *            - Operador a actualizar.
	 * @return Json con el cliente que actualizo o Json con el error que se
	 *         produjo
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOperador(Operador operador) {
		TM tm = new TM(getPath());
		try {
			tm.updateOperador(operador);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(operador).build();
	}
	
	@PUT
	@Path("habilitarOperador/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOperador(@PathParam("id") int id) {
		TM tm = new TM(getPath());
		try {
			tm.habilitarOperador(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(id).build();
	}

	/**
	 * Metodo que expone servicio REST usando DELETE que elimina el operador
	 * que recibe en Json <b>URL: </b> http://"ip o nombre de
	 * host":8080/AlhoAndes/rest/operadores
	 * 
	 * @param cliente
	 *            - operador a eliminar.
	 * @return Json con el operador que elimino o Json con el error que se
	 *         produjo
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOperador(Operador operador) {
		TM tm = new TM(getPath());
		try {
			tm.deleteOperador(operador);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(operador).build();
	}
	
	
	@GET
	@Path("maximaSolicitud")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response rfc12OperadorMaximo() {

		TM tm = new TM(getPath());
		ArrayList<OperadorRFC12> n;
		try {
			n=tm.rfc12OperadorMaximo();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(n).build();
	}
	
	@GET
	@Path("minimaSolicitud")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response rfc12OperadorMinimo() {

		TM tm = new TM(getPath());
		ArrayList<OperadorRFC12> n;
		try {
			n=tm.rfc12OperadorMaximo();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(n).build();
	}
}

