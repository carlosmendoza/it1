package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class DineroProv {
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="dinero")
	private double dinero;

	public DineroProv(@JsonProperty(value="nombre") String nombre, @JsonProperty(value="dinero") double dinero)
	{
		this.nombre = nombre;
		this.dinero = dinero;
		
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

}
