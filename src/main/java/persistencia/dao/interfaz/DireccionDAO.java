package persistencia.dao.interfaz;

import dto.DireccionDTO;

public interface DireccionDAO {

	public int insert(DireccionDTO direccion);

	public boolean delete(DireccionDTO direccion_a_eliminar);
	
	public boolean update(DireccionDTO direccion);
}
