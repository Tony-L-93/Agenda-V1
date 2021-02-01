package dto;

import java.sql.Date;

public class PersonaReport extends PersonaDTO{

	private String dominio;
	
	public PersonaReport(int idPersona, String nombre, String apellido, String telefono, String email,
			Date fechaCumpleanios, DireccionDTO direccion, LocalidadDTO localidad, String contacto,String dominio) {
		super(idPersona, nombre, apellido, telefono, email, fechaCumpleanios, direccion, localidad, contacto);
		this.dominio = dominio;
		// TODO Auto-generated constructor stub
	}
	
	public PersonaReport(PersonaDTO persona,String dominio) {
		super(persona.getIdPersona(), persona.getNombre(), persona.getApellido(),persona.getTelefono(),persona.getEmail(), 
			  persona.getFechaCumpleanios(),persona.getDireccion(), persona.getLocalidad(), persona.getTipoContacto());
		this.dominio = dominio;
	}
	
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
}
