package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.Reserva;

public class DAOReserva {

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
		
	public DAOReserva() {
		recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los
	 * Reservas de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM Reserva
	 * 
	 * @return Arraylist con los ReservaS de la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Reserva> darReservas() throws SQLException, Exception {
		ArrayList<Reserva> reservas = new ArrayList<Reserva>();

		String sql = "SELECT * FROM Reserva";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			int idCliente = rs.getInt("documentocliente");
			int idOferta = rs.getInt("idOferta");
			Date fechaInicial = rs.getDate("fechaInicial");
			Date fechaFinal = rs.getDate("fechaFinal");

			reservas.add(new Reserva(id, idCliente, idOferta, (java.sql.Date) fechaInicial, (java.sql.Date) fechaFinal ));
		}
		return reservas;
	}


	
	/**
	 * Metodo que, usando la conexión a la base de datos, un 
	 * Reserva de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM Reserva WHERE ID=id
	 * 
	 * @return Reserva con id dado por parametro
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public Reserva buscarReservaPorId(int id) throws SQLException {
	Reserva Reserva = null;
	String sql = "SELECT * FROM Reserva WHERE id =" + id;

	PreparedStatement prepStmt = conn.prepareStatement(sql);
	recursos.add(prepStmt);
	ResultSet rs = prepStmt.executeQuery();
	

	if (rs.next()) {
		int idCliente = rs.getInt("idCliente");
		int idOferta = rs.getInt("idOferta");
	
		Date fechaInicial = rs.getDate("fechaInicial");
		Date fechaFinal = rs.getDate("fechaFinal");
		Reserva = new Reserva(id,idCliente, idOferta,  (java.sql.Date) fechaInicial, (java.sql.Date) fechaFinal );
	}

	return Reserva;
}
	
	
	public void addReserva(Reserva reserva) throws SQLException {
		String sql = "INSERT INTO RESERVA VALUES (";
		sql += reserva.getId() + ",";
		sql += reserva.getIdCliente() + ",";
		sql += reserva.getIdOferta() + ", TO_DATE('";
		sql += reserva.getFechaInicial() +"','YYYY-MM-DD')"+ ",TO_DATE('";
		sql += reserva.getFechaFinal() +"','YYYY-MM-DD')"+ ")";
		
		System.out.println("SENTENCIAAAAAAAA: "+sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		
		
	}


	
	public void eliminarReserva(Reserva serv) throws SQLException {

		String sql = "DELETE FROM RESERVA";
		sql += " WHERE ID = " + serv.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
}
