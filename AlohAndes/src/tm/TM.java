package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import dao.DAOCliente;
import dao.DAOInmueble;
import dao.DAOOferta;
import dao.DAOOperador;
import dao.DAOReserva;
import vos.Cliente;
import vos.Inmueble;
import vos.Oferta;
import vos.Operador;
import vos.Reserva;


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


	public void addReserva(Reserva reserva) throws SQLException {
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


	public void deleteOferta(Oferta oferta) throws SQLException {

		DAOOferta daoOferta = new DAOOferta();
		try {
			////// transaccion
			this.conn = darConexion();
			daoOferta.setConn(conn);
			daoOferta.deleteOferta(oferta);

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






	
	
	

}
