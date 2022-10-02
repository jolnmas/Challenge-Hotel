package com.Jo.hotel.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Jo.hotel.controller.ReservaController;
import com.Jo.hotel.modelo.Reserva;
import com.Jo.hotel.modelo.User;


import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

public class AccountClient extends JFrame {
	
	private JPanel contentPane;
	public JLabel label_Bienvenido;
	public JLabel label_type_account;
	private JTable tabla_reservas;
	public Integer cantidadTotalReservas;
	private JDateChooser dateChooser_entrada;
	private JDateChooser dateChooser_salida;
	private JSpinner spinner_cantidadPersonas;
	private JComboBox comboBox_pago;
	private Long diasDeReservas;
	private JLabel label_valor;
	private ArrayList<Reserva> arrayReserva;
	private User userAccount;
	private Boolean modificarChanged=false;
	private Date fechaActual=new Date();
	

	
	public AccountClient(User user) {
		userAccount=user;
		arrayReserva= new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mis Reservas");
		lblNewLabel.setBounds(383, 48, 130, 14);
		contentPane.add(lblNewLabel);
		
		label_type_account = new JLabel("Bienvenido ...");
		label_type_account.setBounds(13, 48, 116, 14);
		contentPane.add(label_type_account);
		
		label_Bienvenido = new JLabel("Account");
		label_Bienvenido.setBounds(13, 23, 222, 14);
		contentPane.add(label_Bienvenido);
		
		JLabel lblNewLabel_2 = new JLabel("Panel de control de Reservas");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(235, 10, 256, 14);
		contentPane.add(lblNewLabel_2);
		
		tabla_reservas = new JTable();
		tabla_reservas.setBounds(235, 73, 375, 96);
		contentPane.add(tabla_reservas);
		tabla_reservas.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "FechaInicio", "FechaFinal", "valor", "Forma De Pago"},
				{"1", "202215115", "200151", "", null},
				{null, "", "", "", null},
				{"", "", "", "", null},
				{"", "", "", "", null},
				{"", "", null, "", null},
			},
			new String[] {
				"ID", "FechaInicio", "New column", "New column", "New column"
			}
		));
		
		JButton btnNewButton_1 = new JButton("Eliminar ");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarReserva();
			}
		});
		btnNewButton_1.setBounds(245, 180, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Modificar ");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}
		});
		btnNewButton_3.setBounds(376, 180, 96, 23);
		contentPane.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(13, 73, 207, 230);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel(" Nueva Reserva");
		lblNewLabel_7.setBounds(46, 23, 146, 14);
		panel.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Fecha Entrada");
		lblNewLabel_1.setBounds(10, 59, 89, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha Salida");
		lblNewLabel_4.setBounds(109, 59, 89, 14);
		panel.add(lblNewLabel_4);
		
		dateChooser_entrada = new JDateChooser();
		dateChooser_entrada.setBounds(10, 73, 74, 20);
		panel.add(dateChooser_entrada);
		
		dateChooser_salida = new JDateChooser();
		dateChooser_salida.setBounds(109, 73, 74, 20);
		panel.add(dateChooser_salida);
		
		JLabel lblNewLabel_5 = new JLabel("Cantidad Personas");
		lblNewLabel_5.setBounds(10, 104, 101, 14);
		panel.add(lblNewLabel_5);
		
		spinner_cantidadPersonas = new JSpinner();
		spinner_cantidadPersonas.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner_cantidadPersonas.setBounds(143, 101, 40, 20);
		panel.add(spinner_cantidadPersonas);
		
		comboBox_pago = new JComboBox();
		comboBox_pago.setBounds(10, 129, 173, 22);
		panel.add(comboBox_pago);
		comboBox_pago.setModel(new DefaultComboBoxModel(new String[] {"Transferencia Bancaria", "Pago Digital", "Tarjeta de Credito", "Tarjeta de Debito"}));
		
		JButton btnNewButton_4 = new JButton("Calcular");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calcularReserva();
			}
		});
		btnNewButton_4.setBounds(10, 162, 89, 23);
		panel.add(btnNewButton_4);
		
		label_valor = new JLabel("0");
		label_valor.setBounds(154, 166, 91, 14);
		panel.add(label_valor);
		
		JLabel lblNewLabel_3 = new JLabel("Valor: $");
		lblNewLabel_3.setBounds(109, 166, 46, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.setBounds(10, 196, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nuevaReserva();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setBounds(547, 295, 67, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon(AccountClient.class.getResource("/com/Jo/hotel/img/background_2.png")));
		lblNewLabel_6.setBounds(0, 0, 692, 329);
		contentPane.add(lblNewLabel_6);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login= new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		
		setInfoAccount(user);
	}
	
	public void limpiarTabla() {
		for (int i=1;i<tabla_reservas.getRowCount();i++ ) {
			tabla_reservas.setValueAt("", i, 0);
			tabla_reservas.setValueAt("", i, 1);
			tabla_reservas.setValueAt("", i, 2);
			tabla_reservas.setValueAt("", i, 3);
			tabla_reservas.setValueAt("", i, 4);
		}
	}
	
	public void actualizarTabla() {
		limpiarTabla();
        ReservaController checkReservas=new ReservaController();
		List <Reserva> reservasTotal =checkReservas.listar(userAccount.getUserId());
		checkReservas=null;
		cantidadTotalReservas=reservasTotal.size();

		if(cantidadTotalReservas<=5) {
			int i=0;
			for (Reserva reserve: reservasTotal) 
				{
				i++;
				tabla_reservas.setValueAt(reserve.getId(), i, 0);
				tabla_reservas.setValueAt(reserve.getFechaEntrada(), i, 1);
				tabla_reservas.setValueAt(reserve.getFechaSalida(), i, 2);
				tabla_reservas.setValueAt(reserve.getValor(), i, 3);
				tabla_reservas.setValueAt(reserve.getFormaDePago(), i, 4);
		    }
		}
	}
	
	
	
	public void setInfoAccount(User user) {
		limpiarTabla();
		label_Bienvenido.setText("Bienvenido ... "+user.getNombre()+" "+user.getApellido());
		label_type_account.setText("UserId: "+user.getUserId());
		
		ReservaController checkReservas=new ReservaController();
		
		List <Reserva> reservas =checkReservas.listar(Integer.valueOf(user.getUserId()));
		checkReservas=null;
		cantidadTotalReservas=reservas.size();
		if(cantidadTotalReservas<=5) {
			int i=0;
			for (Reserva elemReserva: reservas) {
				i++;
				tabla_reservas.setValueAt(elemReserva.getId(), i, 0);
				tabla_reservas.setValueAt(elemReserva.getFechaEntrada(), i, 1);
				tabla_reservas.setValueAt(elemReserva.getFechaSalida(), i, 2);
				tabla_reservas.setValueAt(elemReserva.getValor(), i, 3);
				tabla_reservas.setValueAt(elemReserva.getFormaDePago(), i, 4);
			}
		}
	}
	
	
	public void eliminarReserva() {
		
		var indexSelecteds=tabla_reservas.getSelectedRow();
		if(indexSelecteds>0) {
			var valueIndex=tabla_reservas.getValueAt(indexSelecteds,0);
			if(valueIndex!=""){
				ReservaController eliminarReserva=new ReservaController();
				int removeYesCancel = JOptionPane.showConfirmDialog(null, "¿Are you sure you want to delete this?", "Hotel", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
				if(removeYesCancel == 0) {
					var id= Integer.valueOf(valueIndex.toString());
					eliminarReserva.eliminar(id);
					actualizarTabla();
					eliminarReserva=null;
				}
			}	
		}
		
		
	}
	
	public void limpiarFormulario() {
		dateChooser_entrada.setDate(null);
		dateChooser_salida.setDate(null);
		spinner_cantidadPersonas.setValue(1);
		comboBox_pago.setSelectedIndex(0);
		label_valor.setText("0");
	}
	

	
	public void nuevaReserva() {
		
		if(!modificarChanged) {
			 
			var valor=  Double.valueOf(   label_valor.getText());
			if(valor!=0) {
				int ventanaYesCancel = JOptionPane.showConfirmDialog(null, "¿Confirmar Reserva? Valor $"+label_valor.getText(), "Hotel", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
				if(ventanaYesCancel == 0) {
					  limpiarFormulario();
					  crearNuevaReserva();
					  actualizarTabla();
				}	
			}
			else {
				JOptionPane.showMessageDialog(null, "Something went wrong", "Info", JOptionPane.INFORMATION_MESSAGE);
			 }
	   }
	}
	
	
	public void calcularReserva() {
		boolean validDate=false;
		var fechaEntrada=dateChooser_entrada.getDate();
		var fechaSalida=dateChooser_salida.getDate();

		if(fechaEntrada!=null && fechaSalida!=null) {
			long dateEntrada = fechaEntrada.getTime();
			long dateActual = fechaActual.getTime();
			long dateSalida = fechaSalida.getTime();
			
			long timeDiff = (dateEntrada - dateActual);
			long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
			
			long timeDiff2 = (dateSalida- dateEntrada);
			long cantidadDeDias = TimeUnit.DAYS.convert(timeDiff2, TimeUnit.MILLISECONDS);

			if(daysDiff>=0) {
				if(cantidadDeDias>0) {
					validDate=true;
					diasDeReservas=cantidadDeDias;
				}
			}
		}
		
		if(validDate) {
			var cantidadPersonas=spinner_cantidadPersonas.getValue().toString();
			Integer sum=Integer.valueOf(diasDeReservas.toString())+Integer.valueOf(cantidadPersonas);
			Double valor= 1000 + Math.pow(sum, 3);
		    label_valor.setText(valor.toString());
		    String formaPago=comboBox_pago.getSelectedItem().toString();
		    
		    java.sql.Date dateIn = new java.sql.Date(fechaEntrada.getTime());
		    java.sql.Date dateOut = new java.sql.Date(fechaSalida.getTime());
		    arrayReserva.clear();
				
			Reserva reserva= new Reserva(
						dateIn,
						dateOut,
						valor,
						formaPago,
						userAccount.getUserId()
						);	

		    arrayReserva.add(reserva);	
		}
		else {
			JOptionPane.showMessageDialog(null, "Something went wrong", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public void crearNuevaReserva() {
		
		ReservaController checkReservas=new ReservaController();
		List <Reserva> reservaTotal =checkReservas.listar(userAccount.getUserId());
		var maxReservas=reservaTotal.size();
		checkReservas=null;
			
		if(maxReservas<5) {
			if(arrayReserva.size()>0) {
				ReservaController nuevaReserva= new ReservaController();
				nuevaReserva.guardar(arrayReserva.get(0));
				nuevaReserva=null;
				JOptionPane.showMessageDialog(null, "New book has been addded to the database", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Need to be calculate before confirm", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
	    }
		else {
			
			JOptionPane.showMessageDialog(null, "You have reached the maximum number of books allowed", "Info", JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	
	
	
	public void modificar() {
		var indexSelected=tabla_reservas.getSelectedRow();
		 if(indexSelected>0) {
				var valueOfIndex=tabla_reservas.getValueAt(indexSelected,0);
				
				if(valueOfIndex!="") {
					JDateChooser jd_entrada = new JDateChooser();
					String message ="Elige una fecha de Entrada:\n";
					Object[] params = {message,jd_entrada};
					
					if (JOptionPane.showConfirmDialog(this, params, "Start date", JOptionPane.PLAIN_MESSAGE) == 0) { 
					
					    long dateUpdateEntrada = jd_entrada.getDate().getTime();
						long dateActual = fechaActual.getTime();
						long timeDifference = (dateUpdateEntrada - dateActual);
						long daysDiffiference = TimeUnit.DAYS.convert(timeDifference, TimeUnit.MILLISECONDS);
						
						if(daysDiffiference>0) {
							
							JDateChooser jd_salida = new JDateChooser();
							String message2 ="Elige una fecha de Salida:\n";
							Object[] params1 = {message2,jd_salida};
							if (JOptionPane.showConfirmDialog(this, params1, "Start date", JOptionPane.PLAIN_MESSAGE) == 0) { 
							    
								 long dateUpdateSalida = jd_salida.getDate().getTime();
								 long timeDifference2 = (dateUpdateSalida-dateUpdateEntrada);
								 long daysDiffiference2 = TimeUnit.DAYS.convert(timeDifference2, TimeUnit.MILLISECONDS);
									 
								 if(daysDiffiference2>0) {
										 String message3 ="Ingresa cantidad de personas:\n";
										 Object[] params3 = {message3,spinner_cantidadPersonas};
										 
										 if (JOptionPane.showConfirmDialog(this, params3, "Start date", JOptionPane.PLAIN_MESSAGE) == 0) {  
											    var cantidadPersonas=Integer.valueOf(spinner_cantidadPersonas.getValue().toString());
												String message4 ="Seleciona una forma de pago:\n";
												Object[] params4 = {message4,comboBox_pago};
												
												if (JOptionPane.showConfirmDialog(this, params4, "Start date", JOptionPane.PLAIN_MESSAGE) == 0) { 
													var formaPagoUpdate=comboBox_pago.getSelectedItem().toString();
													Double valorTotal=Double.valueOf(cantidadPersonas+daysDiffiference2);
													Double valorFinal=1000+Math.pow(valorTotal, 3);
													   
													int updateYesCancel = JOptionPane.showConfirmDialog(null, "¿El costo será de  $ "+valorFinal, "Hotel", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
													
													if(updateYesCancel == 0) {
														java.sql.Date dateInUpdate = new java.sql.Date(jd_entrada.getDate().getTime());
													    java.sql.Date dateOutdateInUpdate = new java.sql.Date(jd_salida.getDate().getTime());
													    arrayReserva.clear();
													    Integer idUpdated= Integer.valueOf(valueOfIndex.toString());
															
														Reserva reserva= new Reserva(
																	idUpdated,
																	dateInUpdate,
																	dateOutdateInUpdate,
																	valorFinal,
																	formaPagoUpdate,
																	userAccount.getUserId()
																	);	
															
														arrayReserva.add(reserva);
														  
														establecerActualizacionReserva();
														}
													}
											    }
									}
							 }
						}
					}
				}
			 }
		}
	
	public void establecerActualizacionReserva() {
		
		if(arrayReserva.size()>0) {
			ReservaController nuevaReserva= new ReservaController();
			nuevaReserva.modificar(arrayReserva.get(0));
			nuevaReserva=null;
			actualizarTabla();
		}
	}
}
