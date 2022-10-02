package com.Jo.hotel.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.Jo.hotel.controller.ReservaController;
import com.Jo.hotel.controller.UserController;
import com.Jo.hotel.modelo.Reserva;
import com.Jo.hotel.modelo.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AccountAdmin extends JFrame {

	private JPanel contentPane;
	public JLabel label_info_nombre;
	private JLabel label_account_type;
	private JTextArea texArea;
	private JLabel label_capabilityHotel;
	private JLabel label_time;
	private JLabel label_img;
	private JLabel lblNClientesregistadros;

	
	
	public AccountAdmin(User user) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Panel de Administrador HOTEL");
		lblNewLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 15));
		lblNewLabel.setBounds(190, 0, 246, 25);
		contentPane.add(lblNewLabel);
		
		label_info_nombre = new JLabel("Bienvenido ...");
		label_info_nombre.setBounds(10, 77, 253, 14);
		contentPane.add(label_info_nombre);
		
		label_account_type = new JLabel("Account Type:");
		label_account_type.setBounds(10, 52, 209, 14);
		contentPane.add(label_account_type);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login= new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(438, 335, 89, 23);
		contentPane.add(btnNewButton);
		
		label_capabilityHotel = new JLabel("Capacidad de hotel al:  0%");
		label_capabilityHotel.setBounds(10, 102, 198, 14);
		contentPane.add(label_capabilityHotel);
		
		JButton btnNewButton_1 = new JButton("Reservas");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarPorReservas();
			}
		});
		btnNewButton_1.setBounds(240, 107, 123, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clientes");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listarClientes();
			}
		});
		btnNewButton_2.setBounds(414, 107, 113, 35);
		contentPane.add(btnNewButton_2);
		
		texArea = new JTextArea();
		texArea.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane  scroll_textArea= new JScrollPane(texArea);
		scroll_textArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		scroll_textArea.setBounds(33, 152, 492, 172);
		contentPane.add(scroll_textArea);
		
	    label_time = new JLabel("New label");
		label_time.setBounds(10, 27, 123, 14);
		contentPane.add(label_time);
		
		lblNClientesregistadros = new JLabel("N°: Clientes Registadros");
		lblNClientesregistadros.setBounds(10, 127, 209, 14);
		contentPane.add(lblNClientesregistadros);
		
		label_img = new JLabel("New label");
		label_img.setIcon(new ImageIcon(AccountAdmin.class.getResource("/com/Jo/hotel/img/background_2.png")));
		label_img.setBounds(0, 0, 557, 358);
		contentPane.add(label_img);
		
		setInfoAccount(user);
	}
	
	
	public void setInfoAccount(User user) {
		
		label_account_type.setText("Account Type: ADMIN");
		label_info_nombre.setText("Bienvenido... "+user.getNombre()+" "+user.getApellido());
		
		ReservaController reservaController= new ReservaController();
		List <Reserva> allreservas= reservaController.listarAll();
		var numReserves=allreservas.size();
		
		Double maxReserve=120.0;
		Double capabilityHotel=(numReserves*100)/maxReserve;
		label_capabilityHotel.setText("Capacidad Hotel Al:  "+String.format("%.2f", capabilityHotel) +"%");
		
	    LocalDate  fecha=LocalDate.now();
	    label_time.setText("Date: "+fecha.toString());
	    
	    
	    UserController userController= new UserController();
		List <User> allUsers= userController.getUsers();
		var numClientes=allUsers.size();
	    
	    
	   lblNClientesregistadros.setText("N° de Cliente Registrados: "+numClientes);
	}
	
	
	
	public void listarClientes() {
		texArea.setText("");
		UserController gestor= new UserController();
		List <User> allUser= gestor.getUsers();
		
		gestor=null;
		
	
		String text="";
		for(User user: allUser){
			if(user.getJerarciaId()!=1) {
				ReservaController reservaController= new ReservaController();
				List <Reserva> allreservas= reservaController.listar(user.getUserId());
				reservaController=null;
				var numReservas=allreservas.size();
				text=  text
				+ "Id: "+user.getUserId() +" ---> "+"Nombre:  "+user.getNombre()+" "+user.getApellido()+"\n"
				+"  -Nacionalidad: "+ user.getNacionalidad()+"\n"
				+"  -FechaNacimiento: "+ user.getFechaDeNacimiento()+"\n"
					+"   Tel: "+ user.getTelefono()+"\n"
				+"  -Nº Reservas: "+numReservas +"\n"
				+"\n***************************************\n";
			}
		}
		texArea.setText(text);
	}
	
	public void listarPorReservas() {
		texArea.setText("");
		UserController gestor2= new UserController();
		List <User> users= gestor2.getUsers();
		
		gestor2=null;
		
		String text = "";
        for(User user: users){
                ReservaController reservaController= new ReservaController();
				List <Reserva> allreservas= reservaController.listar(user.getUserId());
				reservaController=null;
				for(Reserva reserva: allreservas){
					 text=text
							 +"ID_Reserva: "+reserva.getId()+"\n"
							 +"     -FechaInicio: "+reserva.getFechaEntrada()+"\n"
							 +"     -FechaSalida: "+reserva.getFechaSalida()+"\n"
							 +"     -Valor: "+reserva.getValor()+"\n"
							 +"     -Forma de pago: "+reserva.getFormaDePago()+"\n"
							 +"     -Poseedor de Reserva: "+user.getNombre()+ " "+user.getApellido()
							 +"\n"
							 +"***************************************************"
							 +"\n"
							 ;
				 }
		}
   	    texArea.setText(text);	
	}
}
