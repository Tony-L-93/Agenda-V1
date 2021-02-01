package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import Excepciones.ExcepcionDuplicado;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DireccionDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{

	private static final String insert = "INSERT INTO personas(nombre, apellido, telefono, email, fechaCumpleanios, idDireccion, idLocalidad, TipoContacto) VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; //, idDicrecion
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas p LEFT JOIN direccion d ON  p.idDireccion = d.idDireccion LEFT JOIN localidad l ON p.idLocalidad = l.id;";
	private static final String update = "UPDATE personas set nombre = ?, apellido = ?, telefono = ?, email = ?,fechaCumpleanios = ?, idLocalidad = ?, tipoContacto = ?, idDireccion = ? WHERE idPersona = ?";

	public boolean insert(PersonaDTO persona) throws ExcepcionDuplicado
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setDate(5, persona.getFechaCumpleanios());
			
			if(persona.getDireccion()!=null) {
				statement.setInt(6,persona.getDireccion().getIdDireccion());
			}
			else {
				statement.setNull(6, 0);
			}

			statement.setInt(7, persona.getLocalidad().getId());
			statement.setString(8, persona.getTipoContacto().toString());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		}
		catch (SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionDuplicado("El telefono ya existe");
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
		
		return isInsertExitoso;
	}
	
	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
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
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersonaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String apellido= resultSet.getString("Apellido");
		String tel = resultSet.getString("Telefono");
		String email=resultSet.getString("Email");
		Date cumple=resultSet.getDate("fechaCumpleanios");
		//String localidad=resultSet.getString("Localidad");
		String contacto=resultSet.getString("TipoContacto");
		String calle = resultSet.getString("calle");
		int altura = resultSet.getInt("altura");
		String piso = resultSet.getString("piso");
		String depto = resultSet.getString("dpto");
		int idDireccion = resultSet.getInt("idDireccion");
	
		
		int idLocalidad = resultSet.getInt("id");
		String localidad = resultSet.getString("localidad");
		String provincia = resultSet.getString("provincia");
		String pais = resultSet.getString("pais");

		
		DireccionDTO direccionDto = new DireccionDTO(idDireccion,calle,altura,piso,depto);
		LocalidadDTO localidadDto = new LocalidadDTO(idLocalidad,localidad,provincia,pais);

		return new PersonaDTO(id, nombre, apellido, tel, email, cumple, direccionDto, localidadDto,contacto);
	}
	
	@Override
	public boolean update(PersonaDTO persona) throws ExcepcionDuplicado {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getApellido());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getEmail());
			statement.setDate(5, persona.getFechaCumpleanios());
			statement.setInt(6, persona.getLocalidad().getId());
			statement.setString(7, persona.getTipoContacto().toString());
			if(persona.getDireccion()!=null) {
				statement.setInt(8,persona.getDireccion().getIdDireccion());
			}else {
				statement.setNull(8, 0);
			}
			statement.setInt(9, persona.getIdPersona());

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			throw new ExcepcionDuplicado("El telefono ya existe");
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
