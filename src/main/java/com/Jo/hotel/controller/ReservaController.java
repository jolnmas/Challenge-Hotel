package com.Jo.hotel.controller;
import java.util.List;
import com.Jo.hotel.dao.ReservaDAO;
import com.Jo.hotel.factory.ConnectionFactory;
import com.Jo.hotel.modelo.Reserva;


public class ReservaController {
	
	private ReservaDAO reservaDao;
	
	public ReservaController() {
		var factory= new ConnectionFactory();
		this.reservaDao=new ReservaDAO(factory.recuperaConexion());
	}
	
	public List<Reserva> listar(Integer userId){
		return reservaDao.listarReservas(userId);
	}
	
	
	public List<Reserva> listarAll(){
		return reservaDao.listAllReserve();
	}
	

	public void guardar(Reserva reserva) {
		reservaDao.guardar(reserva);
	}

	
	public  int eliminar(Integer id)  {
		return reservaDao.eliminar(id);		
	}
	
	public  int  modificar( Reserva reserva) {
		return reservaDao.modificar(reserva.getFechaEntrada(),
				reserva.getFechaSalida(),
				reserva.getValor(),
				reserva.getFormaDePago(),
				reserva.getPoseedorId(),
				reserva.getId());
		
	}
	
}
