package com.Jo.hotel.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource datasource;
	
	public ConnectionFactory () {
		var pooledDataSource= new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("root1234");
		pooledDataSource.setMaxPoolSize(120);
		this.datasource=pooledDataSource;
	}
	
	public Connection recuperaConexion() {
		try {
			return   this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	
}
