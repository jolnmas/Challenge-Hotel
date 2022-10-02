package com.Jo.hotel.modelo;

import java.sql.Date;

public class User {
	
	private Integer id;
	private Integer userID;
	private String pass;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private Date fechaDeNacimiento;
	private Integer telefono;
	private Integer jerarquia_id;
	
	
      public User(Integer id, Integer userID, String pass, String nombre, String apellido,String nacionalidad
    		  ,Date fechaDeNacimiento,Integer telefono,Integer jerarquia_id) 
         {
    	  this.id=id;
    	  this.userID=userID;
    	  this.pass=pass;
    	  this.nombre=nombre;
    	  this.apellido=apellido;
    	  this.nacionalidad=nacionalidad;
    	  this.fechaDeNacimiento=fechaDeNacimiento;
    	  this.telefono=telefono;
    	  this.jerarquia_id=jerarquia_id;
 
         }
      

      public User( Integer userID, String pass, String nombre, String apellido,String nacionalidad
    		  ,Date fechaDeNacimiento,Integer telefono,Integer jerarquia_id) 
         {
    	  this.userID=userID;
    	  this.pass=pass;
    	  this.nombre=nombre;
    	  this.apellido=apellido;
    	  this.nacionalidad=nacionalidad;
    	  this.fechaDeNacimiento=fechaDeNacimiento;
    	  this.telefono=telefono;
    	  this.jerarquia_id=jerarquia_id;
 
         }
      
      public String getPass() {
  		return this.pass;
  	}
      
      public String getNombre() {
    	  return this.nombre;
      }
      
  
      public String getApellido() {
    	  return this.apellido;
      }
      
      public Integer getUserId() {
    	  return this.userID;
      }
      
      public String getNacionalidad() {
    	  return this.nacionalidad;
      }
      
      public Date getFechaDeNacimiento() {
    	  return this.fechaDeNacimiento;
      }
      
      public Integer getJerarciaId() {
    	  return this.jerarquia_id;
      }
      public Integer getTelefono() {
    	  return this.telefono;
      }
	
   

}
