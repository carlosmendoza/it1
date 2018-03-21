package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Oferta {

	@JsonProperty(value="id")
	private int id;

	@JsonProperty(value="costo")
	private double costo;

	@JsonProperty(value="disponibilidad")
	private  int disponibilidad;
	
	@JsonProperty(value="idOperador")
	private int idOperador;
	
	@JsonProperty(value="capacidadReal")
	private int capacidadReal;
	
	public Oferta(@JsonProperty(value="id") int id , @JsonProperty(value="idOperador") int idOperador,@JsonProperty(value="capacidadReal") int capacidadReal,
			@JsonProperty(value="costo") double costo, 
	@JsonProperty(value="disponibilidad") int disponibilidad )
	{
		this.id = id;
		this.costo = costo;
		this.disponibilidad = disponibilidad;
		this.idOperador = idOperador;
		this.capacidadReal = capacidadReal;
		
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

	
	public int getDisponibilidad()
	
	{
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public int getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}

	public int getCapacidadReal() {
		// TODO Auto-generated method stub
		return capacidadReal;
	}
	public void setCapacidadReal(int capacidadReal) {
		// TODO Auto-generated method stub
		this.capacidadReal = capacidadReal;
	}

	
	
}
