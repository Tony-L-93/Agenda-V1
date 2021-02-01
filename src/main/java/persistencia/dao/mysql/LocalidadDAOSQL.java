package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Excepciones.ExcepcionDuplicado;
import dto.LocalidadDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO{

	private static final String insert = "INSERT INTO localidad(localidad, provincia, pais) VALUES(?, ?, ?)"; //, idDicrecion
	private static final String delete = "DELETE FROM localidad WHERE id = ?";
	private static final String readall = "SELECT * FROM localidad";
	private static final String update = "UPDATE localidad set localidad = ?, provincia = ?, pais = ? WHERE id = ?";
	@Override
	public int insert(LocalidadDTO localidad) throws ExcepcionDuplicado {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		int id = -1;
		try
		{
			statement = conexion.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, localidad.getLocalidad());
			statement.setString(2, localidad.getProvincia());
			statement.setString(3, localidad.getPais());
			
			if(statement.executeUpdate() > 0)
			{
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()){
				       id =rs.getInt(1);
				}
				conexion.commit();
			}
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionDuplicado("Lo localidad, provincia y pais ingresada ya existe");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return id;
	}
	@Override
	public boolean delete(LocalidadDTO localidad_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1,localidad_a_eliminar.getId());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	@Override
	public boolean update(LocalidadDTO localidad) throws ExcepcionDuplicado {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, localidad.getLocalidad());
			statement.setString(2, localidad.getProvincia());
			statement.setString(3, localidad.getPais());
			statement.setInt(4, localidad.getId());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionDuplicado("Lo localidad, provincia y pais ingresada ya existe");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isUpdateExitoso;
	}
	@Override
	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				localidades.add(getLocalidadDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}
	
	private LocalidadDTO getLocalidadDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("id");
		String localidad = resultSet.getString("Localidad");
		String provincia= resultSet.getString("Provincia");
		String pais = resultSet.getString("Pais");
		return new LocalidadDTO(id, localidad, provincia, pais);

	}



}
