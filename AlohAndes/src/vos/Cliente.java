package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente {

	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="documento")
	private int documento;
	
	@JsonProperty(value="rol")
	private String rol;
	
	public Cliente( @JsonProperty(value="nombre") String nombre, @JsonProperty(value="documento") int documento, 
			@JsonProperty(value="rol") String rol)
	{
	
		this.nombre = nombre;
		this.documento = documento;
		this.rol = rol;
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
