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
	
	

		public ArrayList<DineroProv> darDineroProv(String fecha) throws SQLException, Exception {
			ArrayList<DineroProv> servicios = new ArrayList<DineroProv>();
			String fechaA = fecha+"-01-01";
			String fechaC = (Integer.valueOf(fecha)-1)+"-01-01";

			String sql = "SELECT OPERADOR.NOMBRE, SUM(FACTURA.VALOR)AS DINERO FROM FACTURA INNER JOIN RESERVA ON FACTURA.IDRESERVA = RESERVA.ID INNER JOIN OFERTA ON RESERVA.IDOFERTA = OFERTA.ID " + 
					"INNER JOIN OPERADOR ON OFERTA.IDOPERADOR = OPERADOR.ID ";
			sql += "where factura.fecha between TO_DATE('"+fechaC+"','YYYY-MM-DD') and TO_DATE('"+fechaA+"','YYYY-MM-DD')"+" GROUP BY OPERADOR.NOMBRE ";
			System.out.println("..........................................................");
			System.out.println(sql);
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
