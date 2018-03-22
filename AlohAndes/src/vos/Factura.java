package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Factura {

	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="idReserva")
	private int idReserva;
	
	@JsonProperty(value="valor")
	private double valor;
	
	@JsonProperty(value="fecha")
	private Date fecha;
	
	public Factura(@JsonProperty(value="id") int id, @JsonProperty(value="valor") double valor, @JsonProperty(value="fecha") Date fecha,
			@JsonProperty(value="idReserva") int idReserva
	 )
	{
		this.id = id;
		this.valor = valor;
		this.fecha = fecha;
		this.idReserva = idReserva;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
}
