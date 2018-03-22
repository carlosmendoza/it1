package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Oferta;
import vos.OfertaTotal;

public class DAOOfertaTotal {
	
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
	public DAOOfertaTotal() {
		recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que, usando la conexi�n a la base de datos, saca todos los
	 * OfertaTotals de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM OfertaTotal
	 * 
	 * @return Arraylist con los OfertaTotalS de la base de datos.
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
			int capacidadReal = rs.getInt("capacidadReal");
			double costo= rs.getDouble("costo");
			int disponibilidad = rs.getInt("disponibilidad");
			int idOperador = rs.getInt("idOperador");
			ofertas.add(new Oferta(id, idOperador, capacidadReal, costo, disponibilidad));
		}
		return ofertas;
	}


	/**
	 * Metodo que, usando la conexi�n a la base de datos, un 
	 * OfertaTotal de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM OfertaTotal WHERE ID=id
	 * 
	 * @return OfertaTotal con id dado por parametro
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
		int capacidadReal= rs.getInt("capacidadReal");
		int disponibilidad = rs.getInt("disponibilidad");
		int idOperador = rs.getInt("idOperador");
		
		oferta = new Oferta(id, idOperador, capacidadReal, costo, disponibilidad);
	}

	return oferta;
}


	public void addOferta(OfertaTotal ofertaTotal) throws SQLException {
		String sql = "INSERT INTO Oferta VALUES (";
		sql += ofertaTotal.getIdOferta() + ",";
		sql += ofertaTotal.getIdOperador() + ",";
		sql += ofertaTotal.getCapacidadReal() + ",";
		sql += ofertaTotal.getCosto()+",";
		sql += ofertaTotal.getDisponibilidad() + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		System.out.println(sql);
		prepStmt.executeQuery();
		
		String sql2 = "INSERT INTO INMUEBLE VALUES (";
		sql2 += ofertaTotal.getIdInmueble() + ",";
		sql2 += ofertaTotal.getIdOferta() + ",'";
		sql2 += ofertaTotal.getTipo() + "','";
		sql2 += ofertaTotal.getCategoria() + "',";
		sql2 += ofertaTotal.getTamanio() + ",'";
		sql2 += ofertaTotal.getUbicacion() + "')";
		
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		System.out.println(sql2);
		prepStmt2.executeQuery();
		
		System.out.println("llega3");
		String listaServicios = ofertaTotal.getServicios().split("-")[0];
		
		System.out.println("llega2");
			String sql3 = "INSERT INTO PRESTAN VALUES (";
			sql3 += ofertaTotal.getIdOferta() + ",";
			sql3 += listaServicios+")";
			
			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);
			
			prepStmt3.executeQuery();
			
			
		
	}



	public void deleteOfertaTotal(OfertaTotal oferta) throws SQLException {

		String sql = "DELETE FROM OFERTA";
		sql += " WHERE ID = " + oferta.getIdOferta();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}

}
