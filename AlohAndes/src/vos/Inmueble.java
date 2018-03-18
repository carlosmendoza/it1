package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Inmueble {
	@JsonProperty(value="id")
	private int id;

	@JsonProperty(value="ubicacion")
	private String ubicacion;

	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="categoria")
	private String categoria;


	@JsonProperty(value="tamanio")
	private String tamanio;
	
	public Inmueble(@JsonProperty(value="id") int id, @JsonProperty(value="ubicacion") String ubicacion, @JsonProperty(value="tipo") String tipo,
			@JsonProperty(value="categoria") String categoria, @JsonProperty(value="tamanio") String tamanio )
	{
		this.id= id;
		this.ubicacion= ubicacion;
		this.tipo = tipo;
		this.categoria = categoria;
		this.tamanio = tamanio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}




}
