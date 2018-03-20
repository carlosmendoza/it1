package vos;


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
	
	
	@JsonProperty(value="horaInicial")
	private String horaInicial;
	
	@JsonProperty(value= "horaFinal")
	private String horaFinal;

	@JsonProperty(value="nit")
	private int nit;
	
	public Operador(@JsonProperty(value="id") int id,@JsonProperty(value="nombre")String nombre, @JsonProperty(value="tipo")String tipo,@JsonProperty(value="documento")int documento,
			@JsonProperty(value="horaInicial") String horaInicial, @JsonProperty(value="horaFinal") String horaFinal, @JsonProperty(value="nit") int nit	)
	{
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.documento = documento;
	    this.horaInicial=horaInicial;
	    this.horaFinal=horaFinal;
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



	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	

}
