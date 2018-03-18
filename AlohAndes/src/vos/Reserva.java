package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Reserva {
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="estado")
	private String estado;
	
	@JsonProperty(value="fechaInicial")
	private Date fechaInicial;
	
	@JsonProperty(value="fechaFinal")
	private Date fechaFinal;
	
	public Reserva (@JsonProperty(value="id")int id, @JsonProperty(value="estado") String estado, @JsonProperty(value="fechaInicial") Date fechaInicial, 
			@JsonProperty(value="fechaFinal")Date fechaFinal)
	{
		
		this.id = id;
		this.estado = estado;
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

}
