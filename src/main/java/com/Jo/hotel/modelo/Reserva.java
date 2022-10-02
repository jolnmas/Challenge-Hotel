package com.Jo.hotel.modelo;

import java.sql.Date;

public class Reserva {
	private Integer id;
	private Date fechaDeEntreda;
	private Date fechaDeSalida;
	private Double valor;
	private String formaDePago;
	private Integer poseedor_id;
	
	public Reserva(Integer id,Date fechaInicio,Date fechaFinal,Double valor,String formaDePago,Integer poseedor_id) {
		this.id=id;
		this.fechaDeEntreda=fechaInicio;
		this.fechaDeSalida=fechaFinal;
		this.valor=valor;
		this.formaDePago=formaDePago;
		this.poseedor_id=poseedor_id;
	}
	

	public Reserva(Date entrada, Date salida, Double valor,String formaDePago,Integer poseedor_id) {
		this.fechaDeEntreda=entrada;
		this.fechaDeSalida=salida;
		this.valor=valor;
		this.formaDePago=formaDePago;
		this.poseedor_id=poseedor_id;
}
	
	

	public Integer getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return this.fechaDeEntreda;
	}
	
	public Date getFechaSalida() {
		return this.fechaDeSalida;
	}
	
	public Double getValor() {
		return this.valor;
	}
	
	public String getFormaDePago() {
		return this.formaDePago;
	}
	
	public Integer getPoseedorId() {
		return this.poseedor_id;
	}
	
	
	


	
}
