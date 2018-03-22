package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.DineroProv;
import vos.Servicio;

public class DAODinero {

	
	
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
	public DAODinero()
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
	
	

		public ArrayList<DineroProv> darDineroProv() throws SQLException, Exception {
			ArrayList<DineroProv> servicios = new ArrayList<DineroProv>();

			String sql = "SELECT OPERADOR.NOMBRE, SUM(FACTURA.VALOR) AS DINERO FROM OPERADOR INNER JOIN OFERTA ON OPERADOR.ID = OFERTA.IDOPERADOR INNER JOIN RESERVA ON OFERTA.ID = RESERVA.IDOFERTA INNER JOIN FACTURA ON RESERVA.ID = FACTURA.IDRESERVA"
					+ "GROUP BY OPERADOR.ID";

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				double dinero = rs.getDouble("dinero");
				servicios.add(new DineroProv( nombre, dinero));
			}
			return servicios;
		}
	
}
