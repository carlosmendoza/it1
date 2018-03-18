package vos;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Operador 
{
	
	@JsonProperty(value="id")
	private int id;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="tipo")
	private String tipo;
	
	@JsonProperty(value="documento")
	private int documento;
	

	@JsonProperty(value="horario")
	private Date horario;
	
	@JsonProperty(value="nit")
	private int nit;
	
	public Operador(@JsonProperty(value="id") int id,@JsonProperty(value="nombre")String nombre, @JsonProperty(value="tipo")String tipo,@JsonProperty(value="documento")int documento,
			@JsonProperty(value="horario") Date horario, @JsonProperty(value="nit") int nit	)
	{
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.documento = documento;
		this.horario = horario;
		this.nit = nit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}
	

}
