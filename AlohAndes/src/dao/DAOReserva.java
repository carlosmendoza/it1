package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jrockit.jfr.parser.ParseException;
import vos.Inmueble;
import vos.Reserva;
import vos.ReservaMasiva;

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
	
	
		public ArrayList<Date>[] analizarOperaciones(String unidad, String tipoAloha) throws SQLException, java.text.ParseException
	{
		ArrayList<Date> parte1= new ArrayList<Date>();
		ArrayList<Date> parte2= new ArrayList<Date>();
		ArrayList<Date> parte3= new ArrayList<Date>();




		//facturas(alojamientos ocupados) 
		String sql="SELECT fc.* FROM FACTURA fc INNER JOIN RESERVA rs ON fc.RESERVAID= rs.ID  " + 
				" INNER JOIN  OFERTA ofe ON ofe.ID=rs.IDOFERTA" + 
				" INNER JOIN INMUEBLE inm ON inm.IDOFERTA = ofe.ID  " + 
				" WHERE inm.TIPO= " + unidad;

		//mayor demanda
		String sql2="";
		//menor ocupación
		String sql3="";
		//mayor ingreso
		String sql4="";
		if(tipoAloha.equalsIgnoreCase("YEAR"))
		{
			sql2="select conteo, aneo from (SELECT COUNT (*) as conteo, EXTRACT(year from fecha) as aneo " + 
					"FROM ("+sql+")" + 
					"group by  EXTRACT(year from fecha))" + 
					"where(SELECT MAX(CONTEO)" + 
					"FROM(SELECT COUNT (*) as conteo, EXTRACT(year from fecha) as aneo" + 
					"FROM (" +sql + ")"+
					"group by EXTRACT(year from fecha)))=conteo;";

			sql3="select conteo, aneo from (SELECT COUNT (*) as conteo, EXTRACT(year from fecha) as aneo " + 
					"FROM ("+sql+")" + 
					"group by  EXTRACT(year from fecha))" + 
					"where(SELECT MIN(CONTEO)" + 
					"FROM(SELECT COUNT (*) as conteo, EXTRACT(year from fecha) as aneo" + 
					"FROM (" +sql + ")"+
					"group by EXTRACT(year from fecha)))=conteo;";

			sql4="select precion, aneo from (SELECT SUM (VALOR) as PRECIO, EXTRACT(year from fecha) as aneo " + 
					"FROM ("+sql+")" + 
					"group by  EXTRACT(year from fecha))" + 
					"where(SELECT MAX(PRECIO)" + 
					"FROM(SELECT SUM (VALOR) as PRECIO, EXTRACT(year from fecha) as aneo" + 
					"FROM (" +sql + ")"+
					"group by EXTRACT(year from fecha)))=PRECIO;";

		

			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			prepStmt2.executeQuery();

			ResultSet rs2 = prepStmt2.executeQuery();

			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String aneo = rs2.getString("aneo");
				String tf=aneo+"-01-01";
				Date fecha=null;

				fecha = sdf.parse(tf);
				parte1.add(fecha);
			}

			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);			    
			rs2 = prepStmt3.executeQuery();

			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String aneo = rs2.getString("aneo");
				String tf=aneo+"-01-01";
				Date fecha=null;
				fecha = sdf.parse(tf);
				parte2.add(fecha);
			}
			
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			rs2 = prepStmt4.executeQuery();
			
			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String aneo = rs2.getString("aneo");
				String tf=aneo+"-01-01";
				Date fecha=null;

				fecha = sdf.parse(tf);
				parte3.add(fecha);
			}
		}
		else if(tipoAloha.equalsIgnoreCase("MONTH"))
		{
			sql2="select conteo, mes, aneo from (SELECT COUNT (*) as conteo,   EXTRACT( MONTH from fecha) as mes, EXTRACT(year from fecha) as aneo " + 
					"FROM ("+sql+")" + 
					"group by EXTRACT(MONTH from fecha), EXTRACT(year from fecha))" + 
					"where(SELECT MAX(CONTEO)" + 
					"FROM(SELECT COUNT (*) as conteo,   EXTRACT( MONTH from fecha) as mes, EXTRACT(year from fecha) as aneo" + 
					"FROM (" +sql + ")"+
					"group by EXTRACT(MONTH from fecha), EXTRACT(year from fecha)))=conteo;";

			sql3="select conteo, mes, aneo from (SELECT COUNT (*) as conteo,   EXTRACT( MONTH from fecha) as mes, EXTRACT(year from fecha) as aneo " + 
					"FROM ("+sql+")" + 
					"group by EXTRACT(MONTH from fecha), EXTRACT(year from fecha))" + 
					"where(SELECT MIN(CONTEO)" + 
					"FROM(SELECT COUNT (*) as conteo,   EXTRACT( MONTH from fecha) as mes, EXTRACT(year from fecha) as aneo" + 
					"FROM (" +sql + ")"+
					"group by EXTRACT(MONTH from fecha), EXTRACT(year from fecha)))=conteo;";

			sql4="select precio, mes, aneo from (SELECT sum (valor) as precio,   EXTRACT( MONTH from fecha) as mes, EXTRACT(year from fecha) as aneo " + 
					"FROM ("+sql+")" + 
					"group by EXTRACT(MONTH from fecha), EXTRACT(year from fecha))" + 
					"where(SELECT max(precio)" + 
					"FROM(SELECT sum (valor) as precio,   EXTRACT( MONTH from fecha) as mes, EXTRACT(year from fecha) as aneo" + 
					"FROM (" +sql + ")"+
					"group by EXTRACT(MONTH from fecha), EXTRACT(year from fecha)))=precio;";


			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			prepStmt2.executeQuery();

			ResultSet rs2 = prepStmt2.executeQuery();

			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String mes = rs2.getString("mes");
				String aneo = rs2.getString("aneo");
				String tf=aneo+"-"+mes+"-01";
				Date fecha=null;

				fecha = sdf.parse(tf);
				parte1.add(fecha);
			}

			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);			    
			rs2 = prepStmt3.executeQuery();

			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String mes = rs2.getString("mes");
				String aneo = rs2.getString("aneo");
				String tf=aneo+"-"+mes+"-01";
				Date fecha=null;
				fecha = sdf.parse(tf);
				parte2.add(fecha);
			}
			
			PreparedStatement prepStmt4 = conn.prepareStatement(sql4);
			recursos.add(prepStmt4);
			rs2 = prepStmt4.executeQuery();
			
			while (rs2.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String mes = rs2.getString("mes");
				String aneo = rs2.getString("aneo");
				String tf=aneo+"-"+mes+"-01";
				Date fecha=null;

				fecha = sdf.parse(tf);
				parte3.add(fecha);
			}

		}
		else
		{
			System.out.println("ingreso una unidad de tiempo no valida");
		}



		ArrayList<Date>[] fechas=new ArrayList[3];
		fechas[0]= parte1;
		fechas[2]= parte2;
		fechas[3]= parte3;

	
		return fechas;

	
			
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
	
	
	public void addReserva(Reserva reserva) throws Exception {
		
		String sql1 = "SELECT * FROM OFERTA INNER JOIN OPERADORI ON OFERTA.IDOPERADOR = OPERADORI.ID AND OFERTA.ID= "+reserva.getIdOferta();
		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		ResultSet r = prepStmt1.executeQuery();
		if(r.next())
		{
			throw new Exception ("El operador esta inhabilitado");
		}
		
		
		
		
		String sql = "INSERT INTO RESERVA VALUES (";
		sql += reserva.getId() + ",";
		sql += reserva.getIdCliente() + ",";
		sql += reserva.getIdOferta() + ", TO_DATE('";
		sql += reserva.getFechaInicial() +"','YYYY-MM-DD')"+ ",TO_DATE('";
		sql += reserva.getFechaFinal() +"','YYYY-MM-DD')"+ ")";
		
		System.out.println("SENTENCIA: "+sql);
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
	public void eliminarReservaPorId(int id) throws SQLException {

		String sql = "DELETE FROM RESERVA";
		sql += " WHERE ID = " + id;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	public List<Inmueble> darInmueblesDisponibles(String pCaracteristicas) throws SQLException {
		List<Inmueble> inmuebles = new ArrayList<>();
		String fechaI = pCaracteristicas.split("\\:")[0];
		String fechaF = pCaracteristicas.split("\\:")[1].split(";")[0].split("X")[0];
		String[] servicios = pCaracteristicas.split("X")[1].split("-");
		
		
		String sql = "SELECT OFERTA.ID AS IDOFERTA FROM OFERTA LEFT OUTER JOIN (SELECT OFERTA.ID as id1 FROM OFERTA INNER JOIN RESERVA ON OFERTA.ID = RESERVA.IDOFERTA "
				+ "where TO_DATE('"+fechaI+"','YYYY-MM-DD') between reserva.fechainicial and RESERVA.FECHAFINAL or TO_DATE('"+fechaF+"','YYYY-MM-DD') between reserva.fechainicial and RESERVA.FECHAFINAL"+") ON OFERTA.ID = ID1 WHERE ID1 IS NULL";
		System.out.println(sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

	while (rs.next()) {
		int idOfer = rs.getInt("idOferta");
		boolean cumple = true;
		for(int i=0; i<servicios.length;i++)
		{
			String sql2= "SELECT servicio.nombre FROM OFerta inner join prestan on oferta.id = prestan.idoferta and oferta.id ="+idOfer+ "inner join servicio on prestan.idservicio = servicio.id where servicio.nombre ='"+servicios[i]+"'";
			PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
			recursos.add(prepStmt2);
			ResultSet rs2 = prepStmt2.executeQuery();
			if(rs2.next()==false)
			{
				cumple=false;
			}
		}
		if (cumple==true)
		{
			String sql3= "SELECT * FROM inmueble where inmueble.idoferta="+idOfer;
			PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
			recursos.add(prepStmt3);
			ResultSet rs3 = prepStmt3.executeQuery();
			if(rs3.next())
			{
				int id = rs3.getInt("id");
				int idOferta = rs3.getInt("idOferta");
				String tipo = rs3.getString("tipo");
				String categoria = rs3.getString("categoria");
				int tamanio = rs3.getInt("tamanio");
				String ubicacion = rs3.getString("ubicacion");
				inmuebles.add(new Inmueble(id,idOferta,tipo,categoria,tamanio,ubicacion));
			}
		}
	
		
		
		
	}
			
		
		return inmuebles;
	}
	
	public void addReservaMasiva(ReservaMasiva reserva) throws Exception {
		;
		int idMax=0;
		String cadena = reserva.getFechaInicial()+"\\:"+reserva.getFechaFinal()+"X"+reserva.getServicios();
		List<Inmueble> in = darInmueblesDisponibles(cadena);
		for(Inmueble inm: in)
		{
			if(!inm.getCategoria().equals(reserva.getTipo())){in.remove(inm);}
		}
		if(in.size()<reserva.getNumeroH()){throw new Exception("AlohAndes no tiene la capacidad de realizar esta reserva masiva");}
		String sql="select max(id) as nummax from oferta";
		System.out.println("SENTENCIA: "+sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		if(rs.next()) {idMax = rs.getInt("nummax")+1;}
		for(Inmueble inm: in)
		{String sql2="insert into reservamasiva values("+reserva.getId()+","+idMax+")";
		System.out.println("SENTENCIA: "+sql2);
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
		
		String sql3="select * from inmueble inner join oferta on inmueble.idoferta = oferta.id and inmueble.id="+inm.getId();
		System.out.println("SENTENCIA: "+sql3);
		PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
		recursos.add(prepStmt3);
		ResultSet rs2 = prepStmt3.executeQuery();
		if(rs2.next())
		{
			Reserva nReserva = new Reserva(idMax, reserva.getIdCliente(), rs2.getInt("idoferta"), reserva.getFechaInicial(), reserva.getFechaFinal());
			idMax = idMax+1;
			addReserva(nReserva);
		}
		
		}
		
		
		
		
		
	}

	public void eliminarReservaMasiva(int idReserva) throws SQLException {
		
		String sql1 = "SELECT IDRESERVA FROM RESERVAMASIVA WHERE IDRESERVAM= "+idReserva;
		PreparedStatement prepStmt = conn.prepareStatement(sql1);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next())
		{
			eliminarReservaPorId(rs.getInt("idreserva"));
		}
		String sql = "DELETE FROM RESERVAMASIVA";
		sql += " WHERE ID = " + idReserva;

		PreparedStatement prepStmt2 = conn.prepareStatement(sql);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
		
	}
}
