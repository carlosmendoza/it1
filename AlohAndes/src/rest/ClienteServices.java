package rest;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DAOCliente;
import tm.TM;
import vos.Cliente;

public class ClienteServices {
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
	 * Metodo que expone servicio REST usando GET que da todos los clientes de la base de datos. 
	 * <b>URL: </b> http://"ip o nombre de host":8080/AlohAndes/rest/(...)
	 * @return Json con todos los clientes de la base de datos o json con el
	 * error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientes() {
		TM tm = new TM(getPath());
		List<Cliente> clientes;
		try {
			clientes = tm.getAllClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}


	/**
	 * Metodo que expone servicio REST usando GET que da un cliente de la base de datos. 
	 * <b>URL: </b> http://"ip o nombre de host":8080/AlohAndes/rest/(...)
	 * @param id identificador del cliente
	 * @return Json el cliente de la base de datos o json con el
	 * error que se produjo
	 */
	@GET
	@Path("{id: \\d+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getClientePorId(@QueryParam("id") int id) {

		TM tm = new TM(getPath());
		try {
			Cliente cliente = tm.darClientePorId(id);
			return Response.status(200).entity(cliente).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}

	/**
	 * Metodo que expone servicio REST usando POST que agrega el cliente que
	 * recibe en Json <b>URL: </b> http://"ip o nombre de
	 * host":8080/AlohAndes/rest/(...)
	 * 
	 * @param cliente
	 *            - cliente a agregar
	 * @return Json con el cliente que agrego o Json con el error que se
	 *         produjo
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(Cliente cliente) {
		TM tm = new TM(getPath());
		try {
			tm.addCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}

	
	/**
	 * Metodo que expone servicio REST usando PUT que actualiza el cliente que
	 * recibe en Json <b>URL: </b> http://"ip o nombre de
	 * host":8080/AlohAndes/rest/clientes
	 * 
	 * @param cliente
	 *            - cliente a actualizar.
	 * @return Json con el cliente que actualizo o Json con el error que se
	 *         produjo
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCliente(Cliente cliente) {
		TM tm = new TM(getPath());
		try {
			tm.updateCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}

	/**
	 * Metodo que expone servicio REST usando DELETE que elimina el cliente
	 * que recibe en Json <b>URL: </b> http://"ip o nombre de
	 * host":8080/AlhoAndes/rest/clientes
	 * 
	 * @param cliente
	 *            - cliente a eliminar.
	 * @return Json con el cliente que elimino o Json con el error que se
	 *         produjo
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCliente(Cliente cliente) {
		TM tm = new TM(getPath());
		try {
			tm.deleteCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}

}
