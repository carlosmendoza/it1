package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class OfertaTotal {


	@JsonProperty(value="idOferta")
	private int idOferta;

	@JsonProperty(value="costo")
	private double costo;

	@JsonProperty(value="disponibilidad")
	private  int disponibilidad;
	
	@JsonProperty(value="idOperador")
	private int idOperador;
	
	@JsonProperty(value="capacidadReal")
	private int capacidadReal;
	
	@JsonProperty(value="idInmueble")
	private int idInmueble;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="categoria")
	private String categoria;
	

	@JsonProperty(value="tamanio")
	private int tamanio;
	
	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	@JsonProperty(value="servicios")
	private String servicios;
	
	public OfertaTotal(@JsonProperty(value="idOferta") int idOferta , @JsonProperty(value="idOperador") int idOperador,@JsonProperty(value="capacidadReal") int capacidadReal,
			@JsonProperty(value="costo") double costo, 
	@JsonProperty(value="disponibilidad") int disponibilidad, @JsonProperty(value="disponibilidad") int idInmueble, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="categoria") String categoria
	, @JsonProperty(value="tamanio")int tamanio, @JsonProperty(value="ubicacion") String ubicacion, @JsonProperty(value="servicios") String servicios)
	{
		this.idOferta = idOferta;
		this.costo = costo;
		this.disponibilidad = disponibilidad;
		this.idOperador = idOperador;
		this.capacidadReal = capacidadReal;
		this.idInmueble = idInmueble;
		this.tipo = tipo;
		this.categoria = categoria;
		this.tamanio = tamanio;
		this.ubicacion = ubicacion;
		this.servicios = servicios;
		
	}

	public int getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
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

	public int getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
	
}
