package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import dao.DAOCliente;
import dao.DAODinero;
import dao.DAOFactura;
import dao.DAOInmueble;
import dao.DAOOferta;
import dao.DAOOfertaTotal;
import dao.DAOOperador;
import dao.DAOReserva;
import vos.Cliente;
import vos.ClienteU;
import vos.DineroProv;
import vos.Factura;
import vos.IndiceOcupacion;
import vos.Inmueble;
import vos.Oferta;
import vos.OfertaRFC12;
import vos.OfertaTotal;
import vos.Operador;
import vos.OperadorRFC12;
import vos.Reserva;
import vos.ReservaMasiva;


public class TM {

	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	private static String CONNECTION_DATA_PATH;

	private String user;

	private String password;


	private String url;

	private String driver;
	
	private Connection conn;

	
	public TM(String contextPathP) {
		
		try {
			CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} 
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void initializeConnectionData() throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(new File(TM.CONNECTION_DATA_PATH));
		Properties properties = new Properties();
		
		properties.load(fileInputStream);
		fileInputStream.close();
		
		this.url = properties.getProperty("url");
		this.user = properties.getProperty("usuario");
		this.password = properties.getProperty("clave");
		this.driver = properties.getProperty("driver");

	}

	
	private Connection darConexion() throws SQLException {
		
		return DriverManager.getConnection(url, user, password);
	}
	
	///////////////////////////////////
	///requerimiento de consulta 7 ///
	/////////////////////////////////	
	
	public ArrayList<Date>[]  analizarOperaciones(String unidad, String tipoAloha) throws Exception
	{
		ArrayList<Date>[]  fechas;
		DAOReserva daoReserva = new DAOReserva();
		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			fechas= daoReserva.analizarOperaciones(unidad, tipoAloha);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return fechas;	
	}
	
	//requerimiento 12 a
	public ArrayList<OfertaRFC12>  rfc12ReservaMaxima() throws Exception
	{
		ArrayList<OfertaRFC12>  ofertas;
		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertas= daoOferta.rfc12ReservaMaxima();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ofertas;	
	}
	
	//requerimiento 12 b
	public ArrayList<OfertaRFC12>  rfc12ReservaMinima() throws Exception
	{
		ArrayList<OfertaRFC12>  ofertas;
		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertas= daoOferta.rfc12ReservaMinima();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ofertas;	
	}

	//requerimiento 12 c
	public ArrayList<OperadorRFC12>  rfc12OperadorMaximo() throws Exception
	{
		ArrayList<OperadorRFC12>  operadores;
		DAOOperador daoOperador = new DAOOperador();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			operadores= daoOperador.rfc12OperadorMaximo();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;	
	}
	
	//requerimiento 12 c
	public ArrayList<OperadorRFC12>  rfc12OperadorMinimo() throws Exception
	{
		ArrayList<OperadorRFC12>  operadores;
		DAOOperador daoOperador = new DAOOperador();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			operadores= daoOperador.rfc12OperadorMinimo();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;	
	}
	
	public List<Oferta>  ofertasPocaDemanda() throws Exception
	{
		ArrayList<Oferta>  ofertasDemanda= new ArrayList<Oferta>();
		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertasDemanda= (ArrayList<Oferta>) daoOferta.ofertasPocaDemanda();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ofertasDemanda;	
	}

	
///////////////////////////////////
////////// CLIENTE ///////////////
/////////////////////////////////

	public List<Cliente> getAllClientes() throws Exception {
		List<Cliente> clientes;
		DAOCliente daoCliente = new DAOCliente();

		try {
			////// transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			clientes = daoCliente.getClientes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return clientes;
	}

	//requerimiento 13 c
		public ArrayList<Cliente>  rfc13MejoresClientes() throws Exception
		{
			ArrayList<Cliente>  clientes;
			DAOCliente daoCliente = new DAOCliente();
			try {
				////// transaccion
				this.conn = darConexion();
				daoCliente.setConn(conn);
				clientes= daoCliente.rfc13MejoresClientes();

			} catch (SQLException e) {
				System.err.println("SQLException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} catch (Exception e) {
				System.err.println("GeneralException:" + e.getMessage());
				e.printStackTrace();
				throw e;
			} finally {
				try {
					daoCliente.cerrarRecursos();
					if (this.conn != null)
						this.conn.close();
				} catch (SQLException exception) {
					System.err.println("SQLException closing resources:" + exception.getMessage());
					exception.printStackTrace();
					throw exception;
				}
			}
			return clientes;	
		}

	public Cliente darClientePorId(int id) throws Exception {
		Cliente cliente;
		DAOCliente daoCliente = new DAOCliente();
		try {
			////// transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			cliente = daoCliente.buscarClientePorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return cliente;
	}


	public void addCliente(Cliente cliente) throws SQLException {
		DAOCliente daoCliente = new DAOCliente();
		try {
			////// transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.addCliente(cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public void updateCliente(Cliente cliente) throws SQLException {
		DAOCliente daoCliente = new DAOCliente();
		try {
			////// transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.updateCliente(cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

	public void deleteCliente(Cliente cliente) throws SQLException {
		
		DAOCliente daoCliente = new DAOCliente();
		try {
			////// transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.deleteCliente(cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}
	
///////////////////////////////////
////////// OPERADOR //////////////
/////////////////////////////////


	public List<Operador> getAllOperadores() throws Exception {
		List<Operador> operadores;
		DAOOperador daoOperador = new DAOOperador();

		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			operadores = daoOperador.darOperadores();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}


	public void addOperador(Operador operador) throws Exception {
		DAOOperador daoOperador = new DAOOperador();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			daoOperador.addOperador(operador);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void updateOperador(Operador operador) throws SQLException {
		DAOOperador daoOperador = new DAOOperador();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			daoOperador.updateOperador(operador);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		
	}


	public void deleteOperador(Operador operador) throws SQLException {
		DAOOperador daoOperador = new DAOOperador();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			daoOperador.deleteOperador(operador);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}

//////////////////////////////////
////////// OFERTA ///////////////
////////////////////////////////

	public List<Oferta> getAllOfertas() throws Exception {
		System.out.println("metodo get ofertas tm");
		DAOOferta daoOferta= new DAOOferta();
		List<Oferta> ofertas;
		try 
		{
			this.conn = darConexion();
			daoOferta.setConn(conn);
			
			ofertas = daoOferta.getOfertas();
		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOferta.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ofertas;
	}


	public Oferta darOfertaPorId(int idOferta) throws SQLException {
		Oferta oferta;
		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			oferta = daoOferta.buscarOfertaPorId(idOferta);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return oferta;
	}


	public List<Oferta> darOfertasPorOperador(int idOperador) {
		// TODO Auto-generated method stub
		return null;
	}


	public void addOferta(Oferta oferta) throws SQLException {
		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			daoOferta.addOferta(oferta);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		
	}


	public List<Inmueble> getAllInmuebles() throws Exception {
		List<Inmueble> inmuebles;
		DAOInmueble daoInmueble = new DAOInmueble();

		try {
			////// transaccion
			this.conn = darConexion();
			daoInmueble.setConn(conn);
			inmuebles = daoInmueble.darInmuebles();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoInmueble.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return inmuebles;
	}


	public Inmueble darInmueblePorId(int id) throws SQLException {
		Inmueble inmueble;
		DAOInmueble daoInmueble = new DAOInmueble();
		try {
			////// transaccion
			this.conn = darConexion();
			daoInmueble.setConn(conn);
			inmueble = daoInmueble.buscarInmueblePorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoInmueble.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return inmueble;
	}


	public List<Reserva> getAllReservas() throws Exception {
		List<Reserva> reservas;
		DAOReserva daoReserva = new DAOReserva();

		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reservas = daoReserva.darReservas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservas;
	}


	public Reserva darReservaPorId(int id) throws SQLException {
		Reserva reserva;
		DAOReserva daoReserva = new DAOReserva();
		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reserva = daoReserva.buscarReservaPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reserva;
	}


	public void addReserva(Reserva reserva) throws Exception {
		DAOReserva daoReserva = new DAOReserva();
		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoReserva.addReserva(reserva);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void deleteOferta(int id) throws SQLException {

		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			daoOferta.deleteOferta(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public void addFactura(Factura factura) throws SQLException {

		DAOFactura daoFactura = new DAOFactura();
		try {
			////// transaccion
			this.conn = darConexion();
			daoFactura.setConn(conn);
			daoFactura.addFactura(factura);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFactura.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public List<Factura> getAllFacturas() throws Exception {
		List<Factura> facturas;
		DAOFactura daoFactura = new DAOFactura();

		try {
			////// transaccion
			this.conn = darConexion();
			daoFactura.setConn(conn);
			facturas = daoFactura.getFacturas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFactura.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return facturas;
	}


	public void borrarReserva(int reserva) throws SQLException {
		DAOReserva daoReserva = new DAOReserva();
		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoReserva.eliminarReserva(reserva);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		
	}


	public List<DineroProv> getDineroR(String fecha) throws Exception {
		List<DineroProv> clientes;
		DAODinero daoCliente = new DAODinero();

		try {
			////// transaccion
			this.conn = darConexion();
			daoCliente.setConn(conn);
			clientes = daoCliente.darDineroProv(fecha);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return clientes;
	}



	public void addOfertaLista(List<Oferta> oferta) throws SQLException {
		// TODO Auto-generated method stub
		DAOOferta dao = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			dao.setConn(conn);
			dao.addOfertaLista(oferta);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				dao.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public List<OfertaTotal> darOfertasPopulares() {
		List<OfertaTotal> ofertas = null;
		DAOOferta daoOferta = new DAOOferta();

		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertas = daoOferta.getOfertasPopulares();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			try {
				throw e;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				try {
					throw exception;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ofertas;
	}


	public List<IndiceOcupacion> getIndicesOcupacion() {
		List<IndiceOcupacion> ofertas = null;
		DAOOfertaTotal daoOferta = new DAOOfertaTotal();

		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertas = daoOferta.darIndiceDeOcupacion();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			try {
				throw e;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				try {
					throw exception;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ofertas;
	}


	public List<Inmueble> getAllDisponibles(String pCaracteristicas) {
		List<Inmueble> ofertas = null;
		DAOInmueble daoOferta = new DAOInmueble();

		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertas = daoOferta.darInmueblesDisponibles(pCaracteristicas);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			try {
				throw e;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				try {
					throw exception;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ofertas;
	}


	public void addReservaMasiva(ReservaMasiva reservas) throws Exception {

		DAOReserva daoReserva = new DAOReserva();
		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoReserva.addReservaMasiva(reservas);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		
		
	}


	public void borrarReservaMasiva(int idReserva) throws SQLException {
		DAOReserva daoReserva = new DAOReserva();
		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			daoReserva.eliminarReservaMasiva(idReserva);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
		
		
	}


	public void habilitarOperador(int id) throws SQLException {
		DAOOperador daoOperador = new DAOOperador();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOperador.setConn(conn);
			daoOperador.habilitar(id);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		
	}


	public List<ClienteU> getUsoCliente(int id) throws SQLException {
		List<ClienteU> reservas;
		DAOCliente daoReserva = new DAOCliente();

		try {
			////// transaccion
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reservas = daoReserva.darUsoCliente(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservas;
	}






	
	
	

}
