package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta {

	@JsonProperty(value="id")
	private int id;

	@JsonProperty(value="costo")
	private double costo;

	@JsonProperty(value="disponibilidad")
	private boolean disponibilidad;
	
	public Oferta(@JsonProperty(value="id") int id, @JsonProperty(value="costo") double costo, @JsonProperty(value="disponibilidad") boolean disponibilidad )
	{
		this.id = id;
		this.costo = costo;
		this.disponibilidad = disponibilidad;
		
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

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
}
