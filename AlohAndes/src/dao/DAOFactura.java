package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Factura;

public class DAOFactura {

	

	/**
	 * Arraylits de recursos que se usan para la ejecuci�n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexi�n a la base de datos
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
	public DAOFactura() {
		recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que, usando la conexi�n a la base de datos, saca todos los
	 * Facturas de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM Factura
	 * 
	 * @return Arraylist con los FacturaS de la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Factura> getFacturas() throws SQLException, Exception {
		ArrayList<Factura> facturas = new ArrayList<Factura>();

		String sql = "SELECT * FROM Factura";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			double valor = rs.getDouble("valor");
			Date fecha = rs.getDate("fecha");
			int idOferta = rs.getInt("idReserva");
			facturas.add(new Factura(id, valor, (java.sql.Date) fecha, idOferta));
		}
		return facturas;
	}





	public void addFactura(Factura Factura) throws SQLException {
		String sql = "INSERT INTO Factura VALUES ('";
		sql += Factura.getId() + "','";
		sql += Factura.getValor() + "','";
		sql += Factura.getFecha() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}


	public void updateFactura(Factura Factura) throws SQLException {
		String sql = "UPDATE Factura SET ";
		sql += "VALOR ='" + Factura.getValor() + "',";
		sql += "FECHA = '" + Factura.getFecha() + "'";
		sql += "WHERE ID=" + Factura.getId() + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}


	public void deleteFactura(Factura factura) throws SQLException {

		String sql = "DELETE FROM Factura";
		sql += " WHERE ID = " + factura.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
}
