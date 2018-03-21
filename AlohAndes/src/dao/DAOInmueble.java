package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Inmueble;


public class DAOInmueble {

	
	
	public final static String INMUEBLE = "INMUEBLE";
	
	
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
	public DAOInmueble()
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
	

	public void setConn(Connection con) {
		this.conn = con;
	}
	

		public ArrayList<Inmueble> darInmuebles() throws SQLException, Exception {
			ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();

			String sql = "SELECT * FROM INMUEBLE";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int idOferta = rs.getInt("idOferta");
				String tipo = rs.getString("tipo");
				String categoria = rs.getString("categoria");
				int tamanio = rs.getInt("tamanio");
				String ubicacion = rs.getString("ubicacion");
				inmuebles.add(new Inmueble(id,idOferta,tipo,categoria,tamanio,ubicacion));
			}
			return inmuebles;
		}
	

	
		public Inmueble buscarInmueblePorId(int id) throws SQLException {
		Inmueble inmueble = null;
		String sql = "SELECT * FROM inmueble WHERE id =" + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		

		if (rs.next()) {
			
			
			int idOferta = rs.getInt("idOferta");
			String tipo = rs.getString("tipo");
			String categoria = rs.getString("categoria");
			int tamanio = rs.getInt("tamanio");
			String ubicacion = rs.getString("ubicacion");
			inmueble = new Inmueble(id,idOferta,tipo,categoria,tamanio,ubicacion);
		}

		return inmueble;
	}
		
		
		public void addInmueble(Inmueble inmueble) throws SQLException {
			String sql = "INSERT INTO Inmueble VALUES ('";
			sql += inmueble.getId() + ",'";
			sql += inmueble.getTipo() + "','";
			sql += inmueble.getCategoria() + "',";
			sql += inmueble.getTamanio()+ ",'";
			sql += inmueble.getUbicacion()+ "')";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}


		public void updateInmueble(Inmueble inmueble) throws SQLException {
			String sql = "UPDATE Inmueble SET ";
			sql += "TIPO ='" + inmueble.getTipo() + "',";
			sql += "CATEGORIA = " + inmueble.getCategoria() +" ";
			sql += "WHERE ID=" + inmueble.getId() + "";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
		
		public void eliminarInmueble(Inmueble serv) throws SQLException {

			String sql = "DELETE FROM INMUEBLE";
			sql += " WHERE ID = " + serv.getId();

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
			
		}
	
}