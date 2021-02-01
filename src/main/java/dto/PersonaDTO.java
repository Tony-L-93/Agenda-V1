package dto;

import java.sql.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private Date fechaCumpleanios;
	private LocalidadDTO localidad;
	private DireccionDTO direccion;
	private String tipoContacto;
	
	public PersonaDTO(int idPersona, String nombre, String apellido, String telefono, String email,  
			Date fechaCumpleanios, DireccionDTO direccion,LocalidadDTO localidad, String contacto) {

		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido= apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.localidad=localidad;
		this.fechaCumpleanios = fechaCumpleanios;
		this.tipoContacto = contacto;
	}

	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getApellido() 
	{
		return this.apellido;
	}

	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}


	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getFechaCumpleanios() {
		return fechaCumpleanios;
	}

	public void setFechaCumpleanios(Date fechaCumpleanios) {
		this.fechaCumpleanios = fechaCumpleanios;
	}
	
	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}	
}
