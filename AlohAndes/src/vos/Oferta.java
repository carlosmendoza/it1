package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta {

	@JsonProperty(value="id")
	private int id;

	@JsonProperty(value="costo")
	private double costo;

	@JsonProperty(value="disponibilidad")
	private boolean disponibilidad;
	
	@JsonProperty(value="idOperador")
	private int idOperador;
	public Oferta(@JsonProperty(value="id") int id, @JsonProperty(value="costo") double costo, 
	@JsonProperty(value="disponibilidad") boolean disponibilidad, @JsonProperty(value="idOperador") int idOperador )
	{
		this.id = id;
		this.costo = costo;
		this.disponibilidad = disponibilidad;
		this.idOperador = idOperador;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public boolean getDisponibilidad()
	
	{
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public int getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}

	
	
}
