package persistencia.dao.interfaz;

import java.util.List;
import Excepciones.ExcepcionDuplicado;
import dto.PersonaDTO;

public interface PersonaDAO 
{
	public boolean insert(PersonaDTO persona) throws ExcepcionDuplicado;

	public boolean delete(PersonaDTO persona_a_eliminar);
	
	public List<PersonaDTO> readAll();
	
	public boolean update(PersonaDTO persona) throws ExcepcionDuplicado;
}
