package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import dto.DireccionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DireccionDAO;

public class DireccionDAOSQL implements DireccionDAO {

	private static final String insert = "INSERT INTO direccion(calle, altura,piso,dpto) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM direccion WHERE idDireccion = ?";
	private static final String update = "UPDATE direccion set calle = ?, altura = ?, piso = ?, dpto = ? WHERE idDireccion = ?";
		
	@Override
	public int insert(DireccionDTO direccion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int id = -1;
		try
		{
			statement = conexion.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, direccion.getCalle());
			statement.setInt(2, direccion.getAltura());
			statement.setString(3, direccion.getPiso());
			statement.setString(4, direccion.getDpto());
			
			if(statement.executeUpdate() > 0)
			{
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()){
				       id =rs.getInt(1);
				}
				conexion.commit();
			}
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
	public boolean delete(DireccionDTO direccion_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(direccion_a_eliminar.getIdDireccion()));
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
	public boolean update(DireccionDTO direccion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, direccion.getCalle());
			statement.setInt(2, direccion.getAltura());
			statement.setString(3, direccion.getPiso());
			statement.setString(4, direccion.getDpto());
			statement.setInt(5, direccion.getIdDireccion());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
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
}
