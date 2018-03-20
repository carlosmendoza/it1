package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Oferta;

public class DAOOferta {

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
	public void setConn(Connection con) {
		this.conn = con;
	}
	
	/**
	 * Metodo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
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
	 * constructor de la clase
	 */
	public DAOOferta() {
		recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los
	 * Ofertas de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM Oferta
	 * 
	 * @return Arraylist con los OfertaS de la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Oferta> getOfertas() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

		String sql = "SELECT * FROM OFERTA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			double costo= rs.getDouble("costo");
			boolean disponibilidad = rs.getBoolean("disponibilidad");
			ofertas.add(new Oferta(id, costo, disponibilidad));
		}
		return ofertas;
	}


	/**
	 * Metodo que, usando la conexión a la base de datos, un 
	 * Oferta de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM Oferta WHERE ID=id
	 * 
	 * @return Oferta con id dado por parametro
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public Oferta buscarOfertaPorId(int id) throws SQLException {
	Oferta oferta = null;
	String sql = "SELECT * FROM Oferta WHERE id =" + id;

	PreparedStatement prepStmt = conn.prepareStatement(sql);
	recursos.add(prepStmt);
	ResultSet rs = prepStmt.executeQuery();
	

	if (rs.next()) {
		
		double costo= rs.getDouble("costo");
		boolean disponibilidad = rs.getBoolean("disponibilidad");
		
		oferta = new Oferta(id, costo, disponibilidad);
	}

	return oferta;
}


	public void addOferta(Oferta oferta) throws SQLException {
		String sql = "INSERT INTO Oferta VALUES ('";
		sql += oferta.getId() + "','";
		sql += oferta.getCosto() + "','";
		sql += oferta.isDisponibilidad() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}


	public void updateOferta(Oferta Oferta) throws SQLException {
		String sql = "UPDATE OFERTA SET ";
		sql += "COSTO ='" + Oferta.getCosto() + "',";	
		sql += "DISPONIBILIDAD = '" + Oferta.isDisponibilidad() + "'";
		sql += "WHERE ID=" + Oferta.getId() + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}


	public void deleteOferta(Oferta oferta) throws SQLException {

		String sql = "DELETE FROM OFERTA";
		sql += " WHERE ID = " + oferta.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
}
