package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Inmueble {
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="idOferta")
	private int idOferta;

	@JsonProperty(value="ubicacion")
	private String ubicacion;

	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="categoria")
	private String categoria;


	@JsonProperty(value="tamanio")
	private int tamanio;
	
	public Inmueble(@JsonProperty(value="id") int id,@JsonProperty(value="idOferta") int idOferta,@JsonProperty(value="tipo") String tipo,
			 @JsonProperty(value="categoria") String categoria,
			 @JsonProperty(value="tamanio") int tamanio, @JsonProperty(value="ubicacion") String ubicacion )
	{
		this.id= id;
		this.ubicacion= ubicacion;
		this.tipo = tipo;
		this.categoria = categoria;
		this.tamanio = tamanio;
		this.idOferta = idOferta;
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

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}




}
