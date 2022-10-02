package com.Jo.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.Jo.hotel.modelo.Reserva;

public class ReservaDAO {
	final private Connection con;
	
	public ReservaDAO(Connection con) {
		this.con=con;
		
	}
	
public List<Reserva> listarReservas(Integer userId) {
		List <Reserva> resultado =new ArrayList<>(); 
		  try(con){
			      final PreparedStatement statement=con.prepareStatement("SELECT ID,fechaDeEntrada, fechaDeSalida,valor,formaDePago,poseedor_userId"
			    		  +" FROM reserva"
			    		  +" WHERE poseedor_userId = ?"
			    		  );
			      try(statement){
			    	  statement.setInt(1,userId);
			    	  statement.execute();
					  ResultSet resultset= statement.getResultSet();
					  while(resultset.next()) 
						  {
						   Reserva elem = new Reserva(
									  resultset.getInt("id"),
									  resultset.getDate("fechaDeEntrada"),
									  resultset.getDate("fechaDeSalida"),
									  resultset.getDouble("valor"),
									  resultset.getString("formaDePago"),
									  resultset.getInt("poseedor_userId")
									  );
							  resultado.add(elem);
						  }
		            }	    		  
		  }catch(SQLException e) {
			  throw new RuntimeException(e);
		  }
		  return resultado;
	}




 public void guardar(Reserva reserva) {
	try(con){
		final PreparedStatement statement= con.prepareStatement(
				"INSERT INTO RESERVA(fechaDeEntrada,fechaDeSalida,valor, formaDePago,poseedor_userId)" 
				+ "VALUES (?,?,?,?,?)",
			    java.sql.Statement.RETURN_GENERATED_KEYS);
        try(statement){
					ejecutaRegistro(reserva,statement);	
				}
	}
	catch(SQLException e){
    	   throw new RuntimeException(e);			
	}
}
 
	public static void ejecutaRegistro(Reserva reserva,PreparedStatement statement)
		throws SQLException {
		statement.setDate(1, reserva.getFechaEntrada());
		statement.setDate(2, reserva.getFechaSalida());
		statement.setDouble(3,reserva.getValor());
		statement.setString(4,reserva.getFormaDePago());
		statement.setInt(5,reserva.getPoseedorId() );
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
	
	public int eliminar(Integer id)  {
		try(con){
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reserva WHERE id =?");
			try(statement){
				statement.setInt(1,id);
				statement.execute();
				int updateCount=statement.getUpdateCount();
				con.close();
				return updateCount;
			}
	   }catch(SQLException e) {
		   throw new RuntimeException(e);
	   }
	}
	
	
	
	
public int modificar(Date  fechaEntrada,Date  fechaSalida,Double valor,String formaDePago,Integer poseedor_userId, Integer id) {
		try(con){
			final PreparedStatement statement = con.prepareStatement("UPDATE reserva SET "
					+ " fechaDeEntrada = ? "
					+ ", fechaDeSalida = ? "
					+ ", valor = ? "
					+ ", formaDePago = ? "
					+ ", poseedor_userId = ? "
					+ " WHERE ID = ? ");
			try(statement){
			        statement.setDate(1, fechaEntrada);
					statement.setDate(2, fechaSalida);
					statement.setDouble(3, valor);
					statement.setString(4, formaDePago);
					statement.setInt(5, poseedor_userId);
					statement.setInt(6, id);
					statement.execute();
					int updateCount=statement.getUpdateCount();
					con.close();
					return updateCount;
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
public List<Reserva> listAllReserve() {
	  List <Reserva> resultado =new ArrayList<>();
	  try(con){
		      final PreparedStatement statement=con.prepareStatement("SELECT ID,fechaDeEntrada,fechaDeSalida,valor,formaDePago,poseedor_userId FROM reserva");
		      try(statement){
				      statement.execute();
					  ResultSet resultset= statement.getResultSet();
					  while(resultset.next()) 
					      {
					       Reserva fila = new Reserva(
								  resultset.getInt("id"),
								  resultset.getDate("fechaDeEntrada"),
								  resultset.getDate("fechaDeSalida"),
								  resultset.getDouble("valor"),
								  resultset.getString("formaDePago"),
								  resultset.getInt("poseedor_userId"));	  
						  resultado.add(fila); 
					  }
	            }
	  }catch(SQLException e) {
		  throw new RuntimeException(e);
	  }
	  return resultado;
 }
}
