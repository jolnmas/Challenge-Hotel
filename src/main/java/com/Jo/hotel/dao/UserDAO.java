package com.Jo.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.Jo.hotel.modelo.User;

public class UserDAO {
	
	final private Connection con;

	public UserDAO(Connection con) {
		this.con=con;	
	}
	
    public List<User> listar(Integer userID, String pass) {
		List <User> resultado =new ArrayList<>();
		  try(con){
			      final PreparedStatement statement=con.prepareStatement("SELECT ID,userID,pass,nombre,apellido,nacionalidad,"
			      		+ "fechaDeNacimiento,telefono,jerarquia_id"
			    		  +" FROM usuario"
			    		  +" WHERE userID = ?"
			    		  +" AND pass = ?"
			    		  );
			      try(statement){
			    	  statement.setInt(1,userID);
			    	  statement.setString(2,pass);
			          statement.execute();
					  ResultSet resultset= statement.getResultSet();
					  while(resultset.next()) 
						  {
						   User fila = new User(
									  resultset.getInt("id"),
									  resultset.getInt("userID"),
									  resultset.getString("pass"),
									  resultset.getString("nombre"),
									  resultset.getString("apellido"),
									  resultset.getString("nacionalidad"),
									  resultset.getDate("fechaDeNacimiento"),
									  resultset.getInt("telefono"),
									  resultset.getInt("jerarquia_id")
									  );
							  resultado.add(fila);
						  }
		            }
		  }catch(SQLException e) {
			  throw new RuntimeException(e);
		  }
		  return resultado;
}
    
    
    public List<User> listarAll() {
		  List <User> resultado =new ArrayList<>(); 
		  try(con){
			      final PreparedStatement statement=con.prepareStatement("SELECT ID,userID,pass,nombre,apellido,nacionalidad,fechaDeNacimiento,telefono,jerarquia_id FROM usuario");
			      try(statement){
					      statement.execute();
						  ResultSet resultset= statement.getResultSet();
						  while(resultset.next()) 
						  {
							  User fila = new User(
									  resultset.getInt("id"),
									  resultset.getInt("userID"),
									  resultset.getString("pass"),
									  resultset.getString("nombre"),
									  resultset.getString("apellido"),
									  resultset.getString("nacionalidad"),
									  resultset.getDate("fechaDeNacimiento"),
									  resultset.getInt("telefono"),
									  resultset.getInt("jerarquia_id"));

							  resultado.add(fila);
	  
						  }
		            }
		  }catch(SQLException e) {
			  throw new RuntimeException(e);
		  }
		  return resultado; 
	}
  
    
    public void guardar(User user) {
		try(con){
			final PreparedStatement statement= con.prepareStatement(
					"INSERT INTO usuario("
					+ "userID,"
					+ "pass,"
					+ "nombre,"
					+ "apellido,"
					+ "nacionalidad,"
					+ "fechaDeNacimiento,"
					+ "telefono,"
					+ "jerarquia_id"
					+ ")"
					+ "VALUES (?,?,?,?,?,?,?,?)",
				    java.sql.Statement.RETURN_GENERATED_KEYS);
	        try(statement){
						ejecutaRegistro(user,statement);	
					}
		}
		catch(SQLException e){
	    	   throw new RuntimeException(e);
						
		}
	}
    
	
	public static void ejecutaRegistro(User user,PreparedStatement statement)
		throws SQLException {
		statement.setInt(1,user.getUserId());
		statement.setString(2,user.getPass());
		statement.setString(3,user.getNombre());
		statement.setString(4,user.getApellido());
		statement.setString(5,user.getNacionalidad());
		statement.setDate(6,user.getFechaDeNacimiento());
		statement.setInt(7,user.getTelefono());
		statement.setInt(8,user.getJerarciaId());
		statement.execute();
		final ResultSet resultSet= statement.getGeneratedKeys();
		try(resultSet)
		    {
			while(resultSet.next()) {
				System.out.println(String.format(
						"Fue Insertado",
						resultSet.getInt(1)));
	
		    }
		}
	
	}

}
