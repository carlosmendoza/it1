package dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.codehaus.jackson.node.BigIntegerNode;

import vos.Operador;


public class DAOOperador {

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
	public DAOOperador() {
		recursos = new ArrayList<Object>();
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
	 * Tipos de operador
	 */
	private static final String PERSONAN= "persona natural";
	private static final String PERSONAE= "persona evento";
	private static final String OVIVIENDAU= "viviendaU";
	private static final String HOTEL= "hotel";
	private static final String HOSTAL= "hostal";

	/**
	 * Metodo que, usando la conexión a la base de datos, saca todos los
	 * operadores de la base de datos 
	 * <b>SQL Statement:</b> SELECT * FROM OPERADOR;
	 * 
	 * @return Arraylist con los restaurantes de la base de datos.
	 * @throws SQLException
	 *             - Cualquier error que la base de datos arroje.
	 * @throws Exception
	 *             - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Operador> darOperadores() throws SQLException, Exception {
		ArrayList<Operador> operadores = new ArrayList<Operador>();

		String sql = "SELECT * FROM OPERADOR";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			try {
				int id  = rs.getInt("id");
				String nombre= rs.getString("nombre");
				long documento = rs.getLong("documento");
				String tipo = rs.getString("tipo");
				String horaInicial = rs.getString("horaInicial");
				String horaFinal = rs.getString("horaFinal");

				long nit = rs.getLong("nit");
		
				operadores.add(new Operador(id,nombre,tipo,(int)documento,(int)nit,horaInicial, horaFinal));
			}catch (Exception e) {
				System.out.println("=====================================: "+rs.getInt("id"));
				e.printStackTrace();
			}
		}
		return operadores;
	}

	
	public void addOperador(Operador operador) throws SQLException,  Exception {
		
		
		if(operador.getTipo()==PERSONAN||operador.getTipo()== PERSONAE)
		{	
			if(operador.getDocumento()==0)
			{
				throw new Exception ("Error, el numero del documento o el tipo de operador es invalido");
			}
			
			
		}
		else if(operador.getTipo()== HOTEL||operador.getTipo()== HOSTAL||operador.getTipo()==OVIVIENDAU)
		{

			if(operador.getNit()==0)
			{
				throw new Exception ("Error, el numero nit o el tipo de operador es invalido");
			}
			
		}
		String sql = "INSERT INTO OPERADOR VALUES (";
		sql += operador.getId() + ",'";
		sql += operador.getNombre() + "','";
		sql += operador.getTipo() + "',";
		sql += operador.getDocumento() + ",";
		sql += operador.getNit() +",'";
		sql += operador.getHoraInicial() + "','";
		sql += operador.getHoraFinal() + "')";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	
	
		
	}

	public void updateOperador(Operador operador) throws SQLException {

		String sql = "UPDATE OPERADOR SET ";
		sql += "ID =" + operador.getId() + ",";
		sql += "NOMBRE = '" + operador.getNombre() + "',";
		sql += "TIPO = '" + operador.getTipo() + "',";
		sql += "DOCUMENTO= " +operador.getDocumento() + ",";
		sql += "NIT = " + operador.getNit() + ",";
		sql += "HORAINICIAL = '" + operador.getHoraInicial() + "',";
		sql += "HORAFINAL = '" + operador.getHoraFinal() + "'";
		sql += "WHERE ID=" + operador.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}

	public void deleteOperador(Operador operador) throws SQLException {

		String sql = "DELETE FROM OPERADOR";
		sql += " WHERE ID = " + operador.getId();

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
	}
	
}
