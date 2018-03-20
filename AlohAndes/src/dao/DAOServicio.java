package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Servicio;
import vos.Servicio;

public class DAOServicio {

	
	
	public final static String SERVICIO = "SERVICIO";
	
	
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;
	
	/**
	 * Metodo constructor que crea DAOAlohAndes <b>post: </b> Crea la
	 * instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOServicio()
	{
		recursos= new ArrayList<Object>();
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
	
	/**
	 * Metodo que inicializa la connection del DAO a la base de datos con la
	 * conexión que entra como parametro.
	 * 
	 * @param con
	 *            - connection a la base de datos
	 */
	public void setConn(Connection con) {
		this.conn = con;
	}
	
	

		/**
		 * Metodo que, usando la conexión a la base de datos, saca todos los
		 * servicios de la base de datos 
		 * <b>SQL Statement:</b> SELECT * FROM SERVICIO
		 * 
		 * @return Arraylist con los SERVICIOS de la base de datos.
		 * @throws SQLException
		 *             - Cualquier error que la base de datos arroje.
		 * @throws Exception
		 *             - Cualquier error que no corresponda a la base de datos
		 */
		public ArrayList<Servicio> darServicios() throws SQLException, Exception {
			ArrayList<Servicio> servicios = new ArrayList<Servicio>();

			String sql = "SELECT * FROM SERVICIOS";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");
				servicios.add(new Servicio(id, nombre, descripcion));
			}
			return servicios;
		}
	

		
		/**
		 * Metodo que, usando la conexión a la base de datos, un 
		 * servicio de la base de datos 
		 * <b>SQL Statement:</b> SELECT * FROM servicio WHERE ID=id
		 * 
		 * @return servicio con id dado por parametro
		 * @throws SQLException
		 *             - Cualquier error que la base de datos arroje.
		 * @throws Exception
		 *             - Cualquier error que no corresponda a la base de datos
		 */
		public Servicio buscarServicioPorId(int id) throws SQLException {
		Servicio servicio = null;
		String sql = "SELECT * FROM servicio WHERE id =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		

		if (rs.next()) {
			
			String nombre = rs.getString("nombre");
			String descripcion = rs.getString("descripcion");
			servicio = new Servicio(id, nombre, descripcion);
		}

		return servicio;
	}
		
		
		public void addServicio(Servicio servicio) throws SQLException {
			String sql = "INSERT INTO Servicio VALUES ('";
			sql += servicio.getId() + "','";
			sql += servicio.getNombre() + "','";
			sql += servicio.getDescripcion() + "')";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public void updateServicio(Servicio servicio) throws SQLException {
			String sql = "UPDATE Servicio SET ";
			sql += "NOMBRE ='" + servicio.getNombre() + "',";
			sql += "DOCUMENTO = " + servicio.getDescripcion() + ",";
			sql += "WHERE ID=" + servicio.getId() + "";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		
		public void eliminarServicio(Servicio serv) throws SQLException {

			String sql = "DELETE FROM SERVICIO";
			sql += " WHERE ID = " + serv.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
	
}
