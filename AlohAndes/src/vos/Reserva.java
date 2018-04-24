package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva {
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="idCliente")
	private int idCliente;
	
	@JsonProperty(value="idOferta")
	private int idOferta;
	
	
	@JsonProperty(value="fechaInicial")
	private Date fechaInicial;
	
	@JsonProperty(value="fechaFinal")
	private Date fechaFinal;
	
	public Reserva (@JsonProperty(value="id")int id,@JsonProperty(value="idCliente")int idCliente,@JsonProperty(value="idOferta")int idOferta, @JsonProperty(value="fechaInicial") Date fechaInicial, 
			@JsonProperty(value="fechaFinal")Date fechaFinal)
	{
		
		this.id = id;
		this.idCliente = idCliente;
		this.idOferta = idOferta;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

}
