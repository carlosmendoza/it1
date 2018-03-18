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

}
