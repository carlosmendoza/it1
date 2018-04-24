package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReservaMasiva {
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="idCliente")
	private int idCliente;
	
	@JsonProperty(value="numeroH")
	private int numeroH;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="servicios")
	private String servicios;
	
	@JsonProperty(value="fechaInicial")
	private Date fechaInicial;
	
	@JsonProperty(value="fechaFinal")
	private Date fechaFinal;
	
	public ReservaMasiva(@JsonProperty(value="id")int id, @JsonProperty(value="numeroH") int numeroH, @JsonProperty(value="tipo") String tipo, 
			@JsonProperty(value="servicios") String servicios,@JsonProperty(value="fechaInicial") Date fechaInicial,@JsonProperty(value="fechaFinal") Date fechaFinal, 
			@JsonProperty(value="idCliente") int idCliente)
	{
		this.numeroH= numeroH;
		this.tipo = tipo;
		this.servicios = servicios;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.idCliente = idCliente;
	}

	public int getNumeroH() {
		return numeroH;
	}

	public void setNumeroH(int numeroH) {
		this.numeroH = numeroH;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdCliente() {
		// TODO Auto-generated method stub
		return idCliente	;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


}
