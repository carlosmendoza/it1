package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vos.Oferta;
import vos.OfertaRFC12;
import vos.OfertaTotal;
import vos.Reserva;

public class DAOOferta {

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
	public DAOOferta() {
		recursos = new ArrayList<Object>();
	}
	
	/**
	 * Metodo que, usando la conexi�n a la base de datos, saca todos los
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

		System.out.println("metodo get ofertas dao");
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
	
	public ArrayList<OfertaRFC12> rfc12ReservaMaxima() throws SQLException
	{
		
		ArrayList<OfertaRFC12> retorno= new ArrayList<OfertaRFC12>();
		//reservas mas ocuapdas
		String sql="Select ofert.*,numeroSemana2 "
				+ "from(select idOferta,numeroSemana2, maximoOcupacion "
				+ "from((select count(*)as numeroOcupacion1, ofe.id as idOferta, to_char(TO_DATE(rs.FECHAINICIAL),'IW') AS numeroSemana1 "
				+ "FROM OFERTA ofe INNER JOIN RESERVA rs ON rs.IDOFERTA=ofe.ID "
				+ "group by ofe.id, to_char(TO_DATE(rs.FECHAINICIAL),'IW') order by ofe.id)"
				+ " INNER JOIN (select max(numeroOcupacion2)as maximoOcupacion, numeroSemana2"
				+ " from(select count(*)as numeroOcupacion2, ofe.id, to_char(TO_DATE(rs.FECHAINICIAL),'IW') AS numeroSemana2 "
				+ "FROM OFERTA ofe INNER JOIN RESERVA rs ON rs.IDOFERTA=ofe.ID group by ofe.id, to_char(TO_DATE(rs.FECHAINICIAL),'IW') ) "
				+ "group by numeroSemana2)ON numeroSemana1=numeroSemana2 and numeroOcupacion1=maximoOcupacion)) "
				+ "inner join OFERTA ofert ON ofert.ID=idOferta order by numeroSemana2;";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			int capacidadReal = rs.getInt("capacidadReal");
			double costo= rs.getDouble("costo");
			int disponibilidad = rs.getInt("disponibilidad");
			int idOperador = rs.getInt("idOperador");
			int numeroSemana2= rs.getInt("numeroSemana2");
			retorno.add(new OfertaRFC12(id, idOperador, capacidadReal, costo, disponibilidad,numeroSemana2));
		}
		
		return retorno;
		
	}
	
	public ArrayList<OfertaRFC12> rfc12ReservaMinima() throws SQLException
	{
		
		ArrayList<OfertaRFC12> retorno= new ArrayList<OfertaRFC12>();
		//reservas mas ocuapdas
		String sql="Select ofert.*,numeroSemana2 "
				+ "from(select idOferta,numeroSemana2, maximoOcupacion "
				+ "from((select count(*)as numeroOcupacion1, ofe.id as idOferta, to_char(TO_DATE(rs.FECHAINICIAL),'IW') AS numeroSemana1 "
				+ "FROM OFERTA ofe INNER JOIN RESERVA rs ON rs.IDOFERTA=ofe.ID "
				+ "group by ofe.id, to_char(TO_DATE(rs.FECHAINICIAL),'IW') order by ofe.id)"
				+ " INNER JOIN (select min(numeroOcupacion2)as maximoOcupacion, numeroSemana2"
				+ " from(select count(*)as numeroOcupacion2, ofe.id, to_char(TO_DATE(rs.FECHAINICIAL),'IW') AS numeroSemana2 "
				+ "FROM OFERTA ofe INNER JOIN RESERVA rs ON rs.IDOFERTA=ofe.ID group by ofe.id, to_char(TO_DATE(rs.FECHAINICIAL),'IW') ) "
				+ "group by numeroSemana2)ON numeroSemana1=numeroSemana2 and numeroOcupacion1=maximoOcupacion)) "
				+ "inner join OFERTA ofert ON ofert.ID=idOferta order by numeroSemana2;";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			int capacidadReal = rs.getInt("capacidadReal");
			double costo= rs.getDouble("costo");
			int disponibilidad = rs.getInt("disponibilidad");
			int idOperador = rs.getInt("idOperador");
			int numeroSemana2= rs.getInt("numeroSemana2");
			retorno.add(new OfertaRFC12(id, idOperador, capacidadReal, costo, disponibilidad,numeroSemana2));
		}
		
		return retorno;
		
	}
		


		public List<Oferta> ofertasPocaDemanda() throws SQLException
	{
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		String sql="SELECT fs.*" + 
				"FROM (SELECT COUNT(*) AS conteo, res.IDOFERTA AS ofertaDemanda FROM RESERVA res INNER JOIN RESERVA rev ON rev.IDOFERTA = res.IDOFERTA" + 
				"WHERE res.ID!=rev.ID AND ((EXTRACT(YEAR from res.FECHAINICIAL) - EXTRACT(YEAR from rev.FECHAFINAL)=0)AND(( (EXTRACT(month from res.FECHAINICIAL) - EXTRACT(month from rev.FECHAFINAL)=1) AND (EXTRACT(DAY from res.FECHAINICIAL) - EXTRACT(DAY from rev.FECHAFINAL)<=0))" + 
				"                           OR ((EXTRACT(month from res.FECHAINICIAL) - EXTRACT(month from rev.FECHAFINAL)=0) AND (EXTRACT(DAY from res.FECHAINICIAL) - EXTRACT(DAY from rev.FECHAFINAL)>=1)))                " + 
				"                           OR" + 
				"                           (((EXTRACT(month from rev.FECHAINICIAL) - EXTRACT(month from res.FECHAFINAL)=1) AND (EXTRACT(DAY from rev.FECHAINICIAL) - EXTRACT(DAY from res.FECHAFINAL)<=0))" + 
				"                           OR ((EXTRACT(month from rev.FECHAINICIAL) - EXTRACT(month from res.FECHAFINAL)=0) AND (EXTRACT(DAY from rev.FECHAINICIAL) - EXTRACT(DAY from res.FECHAFINAL)>=1))))" + 
				"                           group by res.IDOFERTA) INNER JOIN OFERTA fs ON fs.id= ofertaDemanda" + 
				"WHERE conteo!=2;";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs= prepStmt.executeQuery();
		
		while(rs.next()) {
			
			int id= rs.getInt("id");
			int idOperador= rs.getInt("idOperador");
			int capacidad= rs.getInt("capacidadReal");
			double costo= rs.getDouble("costo");
			int disponibilidad=rs.getInt("disponibilidad");
					
			ofertas.add(new Oferta(id,idOperador,capacidad,costo,disponibilidad));
		}
		return ofertas; 

	}
	/**
	 * Metodo que, usando la conexi�n a la base de datos, un 
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
		int capacidadReal= rs.getInt("capacidadReal");
		int disponibilidad = rs.getInt("disponibilidad");
		int idOperador = rs.getInt("idOperador");
		
		oferta = new Oferta(id, idOperador, capacidadReal, costo, disponibilidad);
	}

	return oferta;
}


	public void addOferta(Oferta oferta) throws SQLException {
		String sql = "INSERT INTO Oferta VALUES (";
		sql += oferta.getId() + ",";
		sql += oferta.getIdOperador() + ",";
		sql += oferta.getCapacidadReal() + ",";
		sql += oferta.getCosto()+",";
		sql += oferta.getDisponibilidad() + ")";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}


	public void updateOferta(Oferta Oferta) throws SQLException {
		String sql = "UPDATE OFERTA SET ";
		sql += "COSTO =" + Oferta.getCosto() + ",";	
		sql += "CAPACIDADREAL =" + Oferta.getCosto() + ",";	
		sql += "DISPONIBILIDAD = " + Oferta.getDisponibilidad() + "";
		sql += "WHERE ID=" + Oferta.getId() + "";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}


	public void deleteOferta(int id) throws SQLException {

		String sql = "DELETE FROM OFERTA";
		sql += " WHERE ID = " + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}

	public void addOfertaLista(List<Oferta> oferta) throws SQLException {
		
		for (Oferta o: oferta)
		{
			addOferta(o);
		}
		
	}

	public List<OfertaTotal> getOfertasPopulares() throws SQLException {
		ArrayList<OfertaTotal> ofertas = new ArrayList<OfertaTotal>();

		String sql = "SELECT OFERTA.ID AS IDOFERTA, OFERTA.COSTO, OPERADOR.ID AS IDOPERADOR, INMUEBLE.ID AS IDINMUEBLE, INMUEBLE.TIPO, INMUEBLE.CATEGORIA, INMUEBLE.TAMANIO , INMUEBLE.UBICACION FROM OFERTA INNER JOIN INMUEBLE ON OFERTA.ID = INMUEBLE.IDOFERTA INNER JOIN RESERVA ON RESERVA.IDOFERTA = OFERTA.ID INNER JOIN FACTURA ON FACTURA.IDRESERVA = RESERVA.ID INNER JOIN OPERADOR ON OFERTA.IDOPERADOR= OPERADOR.ID  where ROWNUM < 11 ";
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("idOferta");
			String tipo = rs.getString("tipo");
			double costo= rs.getDouble("costo");
			String categoria = rs.getString("categoria");
			int idOperador = rs.getInt("idOperador");
			int tamanio = rs.getInt("tamanio");
			String ubicacion = rs.getString("ubicacion");
			int idInmueble = rs.getInt("idInmueble");
			ofertas.add(new OfertaTotal(id, idOperador, costo, idInmueble,tipo, categoria, tamanio, ubicacion));
		}
		return ofertas;
	}
}
