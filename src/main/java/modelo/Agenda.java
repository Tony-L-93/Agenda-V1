package modelo;

import java.util.List;

import Excepciones.ExcepcionDuplicado;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DireccionDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;

public class Agenda 
{
	private PersonaDAO persona;	
	private DireccionDAO direccion;
	private LocalidadDAO localidad;
	
	public Agenda(DAOAbstractFactory metodo_persistencia)
	{
		this.persona = metodo_persistencia.createPersonaDAO();
		this.direccion = metodo_persistencia.createDireccionDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona) throws ExcepcionDuplicado
	{
		if(nuevaPersona.getDireccion() != null) {
			int idDireccion = this.direccion.insert(nuevaPersona.getDireccion());
			nuevaPersona.getDireccion().setIdDireccion(idDireccion);
		}
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		this.persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return this.persona.readAll();		
	}
	
	public void editarPersona(PersonaDTO persona_a_editar) throws ExcepcionDuplicado {
		int idDireccion = 0;
		if(persona_a_editar.getDireccion() != null) {
			this.direccion.update(persona_a_editar.getDireccion());
			
			if(!persona_a_editar.getDireccion().getCalle().equals("") && persona_a_editar.getDireccion().getIdDireccion()==0) {
				idDireccion = this.direccion.insert(persona_a_editar.getDireccion());
				persona_a_editar.getDireccion().setIdDireccion(idDireccion);
			}else {
				this.direccion.update(persona_a_editar.getDireccion());
			}
		}
		this.persona.update(persona_a_editar);
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad) throws ExcepcionDuplicado
	{

		this.localidad.insert(nuevaLocalidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad_a_eliminar) 
	{
		this.localidad.delete(localidad_a_eliminar);
	}
	
	public List<LocalidadDTO> obtenerLocalidades()
	{
		return this.localidad.readAll();		
	}
	public void editarLocalidad(LocalidadDTO localidad_a_editar) throws ExcepcionDuplicado {
	
		this.localidad.update(localidad_a_editar);
	}
}
