package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Estandar {

	@JsonProperty(value="documento")
	private int documento;

	@JsonProperty(value="fechaInicial")
	private Date fechaInicial;

	@JsonProperty(value="fechaFinal")
	private Date fechaFinal;

	@JsonProperty(value="nombre")
	private String nombre;

	@JsonProperty(value="rol")
	private String rol;

	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="categoria")
	private String categoria;
	
	@JsonProperty(value="idOferta")
	private int idOferta;
	
	public int getDocumento()
	{
		return documento;
	}
	
	public Estandar (@JsonProperty(value="documento") int documento,@JsonProperty(value="fechaInicial") Date fechaInicial,@JsonProperty(value="fechaFinal") Date fechaFinal
	,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="rol")  String rol, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="categoria")String categoria, @JsonProperty(value="idOferta") int idOferta) {
		this.documento = documento;
		this.fechaInicial= fechaInicial;
		this.fechaFinal = fechaFinal;
		this.nombre = nombre;
		this.rol = rol;
		this.tipo = tipo;
		this.categoria = categoria;
		this.idOferta = idOferta;
		
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
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
	

}
