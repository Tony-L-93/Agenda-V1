package persistencia.dao.interfaz;

import java.util.List;

import Excepciones.ExcepcionDuplicado;
import dto.DireccionDTO;
import dto.LocalidadDTO;

public interface LocalidadDAO {

	public int insert(LocalidadDTO localidad)throws ExcepcionDuplicado;

	public boolean delete(LocalidadDTO localidad_a_eliminar);
	
	public boolean update(LocalidadDTO localidad)throws ExcepcionDuplicado;
	
	public List<LocalidadDTO> readAll();
}
