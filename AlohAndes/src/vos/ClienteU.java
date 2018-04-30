package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteU {

	
	@JsonProperty(value="fechaIncial")
	private Date fechaInicial;
	

	@JsonProperty(value="fechaFinal")
	private Date fechaFinal;



	@JsonProperty(value="categoria")
	private String categoria;

	@JsonProperty(value="tamanio")
	private int tamanio;

	@JsonProperty(value="valor")
	private int valor;
	
	@JsonProperty(value="documentoCliente")
	private int documentoCliente;
	
	
	public ClienteU( @JsonProperty(value="fechaInicial") Date fechaInicial, @JsonProperty(value="fechaFinal") Date fechaFinal, @JsonProperty(value="categoria") String categoria, 
			@JsonProperty(value="tamanio") int tamanio,@JsonProperty(value="valor") int valor, @JsonProperty(value="documentoCliente") int documento)
	{
	this.fechaFinal= fechaFinal;
	this.fechaInicial = fechaInicial;
	this.categoria = categoria;
	this.tamanio= tamanio;
	this.valor= valor;
	this.documentoCliente= documento;
	}

	


}
