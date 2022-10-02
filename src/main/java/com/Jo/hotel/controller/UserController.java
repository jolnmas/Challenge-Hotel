package com.Jo.hotel.controller;

import java.util.List;
import com.Jo.hotel.dao.UserDAO;
import com.Jo.hotel.factory.ConnectionFactory;
import com.Jo.hotel.modelo.User;

public class UserController {
	
	private UserDAO userDao;
	
	
	public UserController() {
		var factory = new ConnectionFactory();
		this.userDao= new UserDAO(factory.recuperaConexion());
	}
	
	
	public List<User> getLogIn(String userID,String pass){
		return userDao.listar(Integer.valueOf(userID),pass);
	}
	
	
	public List<User> getUsers(){
		return userDao.listarAll();
	}

	public void guardar(User user) {
		userDao.guardar(user);
	}
	
	
	

}
