package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vos.Cliente;


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
		 * Metodo que, usando la conexión a la base de datos, saca todos los
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
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int documento = rs.getInt("documento");
				String rol = rs.getString("rol");
				clientes.add(new Cliente(id, nombre, documento, rol));
			}
			return clientes;
		}


		/**
		 * Metodo que, usando la conexión a la base de datos, un 
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
		String sql = "SELECT * FROM CLIENTE WHERE id =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		

		if (rs.next()) {
			
			String nombre = rs.getString("nombre");
			int documento = rs.getInt("documento");
			String rol = rs.getString("rol");
			cliente = new Cliente(id, nombre, documento, rol);
		}

		return cliente;
	}


		public void addCliente(Cliente cliente) throws SQLException {
			String sql = "INSERT INTO CLIENTE VALUES ('";
			sql += cliente.getId() + "','";
			sql += cliente.getNombre() + "','";
			sql += cliente.getDocumento() + "','";
			sql += cliente.getRol() + "')";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public void updateCliente(Cliente cliente) throws SQLException {
			String sql = "UPDATE CLIENTE SET ";
			sql += "NOMBRE ='" + cliente.getNombre() + "',";
			sql += "DOCUMENTO = " + cliente.getDocumento() + ",";
			sql += "ROL = '" + cliente.getRol() + "'";
			sql += "WHERE ID=" + cliente.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public void deleteCliente(Cliente cliente) throws SQLException {

			String sql = "DELETE FROM CLIENTE";
			sql += " WHERE ID = " + cliente.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}

}
