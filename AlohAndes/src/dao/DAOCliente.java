package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vos.Cliente;
import vos.ClienteU;


public class DAOCliente {
	
		public final static String USUARIO = "USUARIO";
	
		private ArrayList<Object> recursos;
	
		private Connection conn;
		
		public void setConn(Connection con) {
			this.conn = con;
		}
		
		
		public void cerrarRecursos() {
			for (Object ob : recursos) {
				if (ob instanceof PreparedStatement)
					try {
						((PreparedStatement) ob).close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		}
			
		public DAOCliente() {
			recursos = new ArrayList<Object>();
		}
		
		
		/**
		 * Metodo que, usando la conexi�n a la base de datos, saca todos los
		 * clientes de la base de datos 
		 * <b>SQL Statement:</b> SELECT * FROM CLIENTE
		 * 
		 * @return Arraylist con los CLIENTES de la base de datos.
		 * @throws SQLException
		 *             - Cualquier error que la base de datos arroje.
		 * @throws Exception
		 *             - Cualquier error que no corresponda a la base de datos
		 */
		public ArrayList<Cliente> getClientes() throws SQLException, Exception {
			
			
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();

			String sql = "SELECT * FROM CLIENTE";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery(sql);
			//System.err.println(rs.next());
			while (rs.next()) {
				System.out.println("WHILE MALDITA  SEA");
				System.out.println("CLIENTE");
				String nombre = rs.getString("nombre");
				int documento = rs.getInt("documento");
				String rol = rs.getString("rol");
				clientes.add(new Cliente( nombre, documento, rol));
			}
			return clientes;
		}


		/**
		 * Metodo que, usando la conexi�n a la base de datos, un 
		 * cliente de la base de datos 
		 * <b>SQL Statement:</b> SELECT * FROM CLIENTE WHERE ID=id
		 * 
		 * @return Cliente con id dado por parametro
		 * @throws SQLException
		 *             - Cualquier error que la base de datos arroje.
		 * @throws Exception
		 *             - Cualquier error que no corresponda a la base de datos
		 */
		public Cliente buscarClientePorId(int id) throws SQLException {
		Cliente cliente = null;
		String sql = "SELECT * FROM CLIENTE WHERE documento =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		

		if (rs.next()) {
			
			String nombre = rs.getString("nombre");
			int documento = rs.getInt("documento");
			String rol = rs.getString("rol");
			cliente = new Cliente(nombre, documento, rol);
		}

		return cliente;
	}


		public void addCliente(Cliente cliente) throws SQLException {
			String sql = "INSERT INTO CLIENTE VALUES (";
			
			sql += cliente.getDocumento() + ",'";
			sql += cliente.getNombre() + "','";
			
			sql += cliente.getRol() + "')";
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public void updateCliente(Cliente cliente) throws SQLException {
			String sql = "UPDATE CLIENTE SET ";
			sql += "NOMBRE ='" + cliente.getNombre() + "', ";
			
			sql += "ROL = '" + cliente.getRol() + "'";
			sql += "WHERE DOCUMENTO=" + cliente.getDocumento(); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public void deleteCliente(Cliente cliente) throws SQLException {

			String sql = "DELETE FROM CLIENTE";
			sql += " WHERE DOCUMENTO = " + cliente.getDocumento();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public List<ClienteU> darUsoCliente(int id) throws SQLException {
			List<ClienteU> clientes = new ArrayList<>();
			String sql = "select RESERVA.DOCUMENTOCLIENTE, FECHAINICIAL, FECHAFINAL, FACTURA.VALOR, TIPO, TAMANIO, CATEGORIA from reserva inner join factura on reserva.id = factura.IDRESERVA inner join inmueble "
					+ "on inmueble.idoferta = reserva.idOferta where reserva.documentocliente = "+id;
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs= prepStmt.executeQuery();
			while(rs.next())
			{
				int doc = rs.getInt("documentocliente");
				Date fi= rs.getDate("fechainicial");
				Date ff= rs.getDate("fechafinal");
				int valor = rs.getInt("valor");
				String tipo = rs.getString("tipo");
				int tamanio = rs.getInt("tamanio");
				String categoria = rs.getString("categoria");
				clientes.add(new ClienteU(fi,ff,categoria,tamanio,valor,doc));
				
			}
			return clientes;
		}


		
}
