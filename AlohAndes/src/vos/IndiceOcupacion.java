package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class IndiceOcupacion {
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="numOfertas")
	private int numOfertas;
	
	@JsonProperty(value="porcentajeO")
	private double porcentajeO;
	
	public IndiceOcupacion( @JsonProperty(value="nombre") String nombre, @JsonProperty(value="numOfertas") int numOfertas,@JsonProperty(value="porcentajeO") double porcentajeO)
	{
	
		this.nombre = nombre;
		this.numOfertas = numOfertas;
		this.porcentajeO = porcentajeO;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumOfertas() {
		return numOfertas;
	}

	public void setNumOfertas(int numOfertas) {
		this.numOfertas = numOfertas;
	}

	public double getPorcentajeO() {
		return porcentajeO;
	}

	public void setPorcentajeO(double porcentajeO) {
		this.porcentajeO = porcentajeO;
	}


	

}
