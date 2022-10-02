package com.Jo.hotel.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.Jo.hotel.controller.UserController;
import com.Jo.hotel.modelo.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Pattern;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.ImageIcon;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField input_userId;
	private JTextField input_pass;
	private JTextField input_nombre;
	private JTextField input_apellido;
	private JTextField input_nacionalidad;
	private JTextField input_telefono;
	private JButton btnCheckAvailability ;
	private Integer nuevoUserId;
	private JButton btnCreateAccount;
	private JDateChooser dateChooser_nacimiento;

	
	public CreateAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		input_userId = new JTextField();
		input_userId.setBounds(138, 22, 86, 20);
		contentPane.add(input_userId);
		input_userId.setColumns(10);
		
		input_pass = new JTextField();
		input_pass.setBounds(138, 53, 86, 20);
		contentPane.add(input_pass);
		input_pass.setColumns(4);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setEnabled(false);
		btnCreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearCuenta();
			}
		});
		btnCreateAccount.setBounds(303, 103, 118, 23);
		contentPane.add(btnCreateAccount);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login= new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(398, 240, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(59, 56, 69, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("UserID:");
		lblNewLabel_2.setBounds(72, 25, 56, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(63, 85, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Apellido:");
		lblNewLabel_4.setBounds(63, 118, 65, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nacionalidad:");
		lblNewLabel_5.setBounds(44, 150, 84, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_6.setBounds(10, 212, 118, 17);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Telefono:");
		lblNewLabel_7.setBounds(63, 179, 65, 14);
		contentPane.add(lblNewLabel_7);
		
		input_nombre = new JTextField();
		input_nombre.setBounds(138, 82, 86, 20);
		contentPane.add(input_nombre);
		input_nombre.setColumns(10);
		
		input_apellido = new JTextField();
		input_apellido.setBounds(138, 115, 86, 20);
		contentPane.add(input_apellido);
		input_apellido.setColumns(10);
		
		input_nacionalidad = new JTextField();
		input_nacionalidad.setBounds(138, 147, 86, 20);
		contentPane.add(input_nacionalidad);
		input_nacionalidad.setColumns(10);
		
		input_telefono = new JTextField();
		input_telefono.setBounds(138, 176, 86, 20);
		contentPane.add(input_telefono);
		input_telefono.setColumns(10);
		
		dateChooser_nacimiento = new JDateChooser();
		dateChooser_nacimiento.setBounds(138, 209, 86, 20);
		contentPane.add(dateChooser_nacimiento);
		
		JLabel lblNewLabel = new JLabel("Create New Account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(44, 0, 168, 14);
		contentPane.add(lblNewLabel);
		
		btnCheckAvailability = new JButton("CHECK AVAILABILITY\r\n");
		btnCheckAvailability.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkUser();
			}
		});
		btnCheckAvailability.setBounds(290, 39, 155, 20);
		contentPane.add(btnCheckAvailability);	
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setIcon(new ImageIcon(CreateAccount.class.getResource("/com/Jo/hotel/img/Building2.png")));
		lblNewLabel_8.setBounds(234, 0, 320, 229);
		contentPane.add(lblNewLabel_8);
	}
	
	public void checkUser() {
		var userid=input_userId.getText();
		
		if(validarVacio(userid)) {
			if(validarNumero(userid)) {
				 if(userid.length()==5) {
					 comprobarUserId(userid); 
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "User ID must be 5 digit", "Info", JOptionPane.INFORMATION_MESSAGE);
					 }	
			}
		}	
	}
	
	public void comprobarUserId(String id) {
		
		UserController allUser= new UserController();
		List<User> usuarios=allUser.getUsers();
		allUser=null;
		boolean usuarioEncontrado=false;

		for(User user : usuarios) {
			var aux1= Integer.valueOf(user.getUserId());
			var aux2=Integer.valueOf(id);
			int resultMatch=aux1-aux2;
			if(resultMatch==0) {
				usuarioEncontrado=true;
			}
		}
		
		if(!usuarioEncontrado) {
			input_userId.setEditable(false);
        	btnCheckAvailability.setEnabled(false);
        	nuevoUserId=Integer.valueOf(id);
        	btnCreateAccount.setEnabled(true);
        	JOptionPane.showMessageDialog(null, "This User ID is available", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "This User ID is not available", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public boolean validarVacio(String elem) {
		if(elem.length()<=0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean validarNullFecha(java.util.Date fechaNacimiento) {
		if(fechaNacimiento==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public  boolean validarNumero(String elem) {
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		return pattern.matcher(elem).matches();
	}
	
	public void crearCuenta() {
		boolean passCheck=false;
		var password=input_pass.getText();
		if(password.length()==4) {
			passCheck=true;
		}
		else {
			JOptionPane.showMessageDialog(null, "The password must be 4 digit", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		var name=input_nombre.getText();
		boolean nameCheck=validarVacio(name);
		
		if(!nameCheck) {
			JOptionPane.showMessageDialog(null, "The field name is incorrect", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		var lastName=input_apellido.getText();
		boolean lastNameCheck=validarVacio(lastName);
		
		if(!lastNameCheck) {
			JOptionPane.showMessageDialog(null, "The field Apellido is incorrect", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		var nacionalidad = input_nacionalidad.getText();
		boolean nacionalidadCheck=validarVacio(nacionalidad);
		
		if(!nacionalidadCheck) {
			JOptionPane.showMessageDialog(null, "Nacionalidad is incorrect", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		var telefono=input_telefono.getText();
		boolean telefonoCheck=  validarNumero(telefono);
		
		if(!telefonoCheck) {
			JOptionPane.showMessageDialog(null, "The number phone is incorrect", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		var fechaNacimiento=dateChooser_nacimiento.getDate();
		var fechaNacimientoCheck=validarNullFecha(fechaNacimiento);
		
		if(!fechaNacimientoCheck) {
			JOptionPane.showMessageDialog(null, "Please choose a date", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if(passCheck && nameCheck && lastNameCheck && nacionalidadCheck && telefonoCheck &&fechaNacimientoCheck) {
			java.sql.Date fechaNac = new java.sql.Date(fechaNacimiento.getTime());
			User nuevoUsuario= new User(
					nuevoUserId,
					password,
					name,
					lastName,
					nacionalidad,
					fechaNac,
					Integer.valueOf(telefono),
					2
				);

			UserController gestor=new UserController();
			gestor.guardar(nuevoUsuario);
			
			gestor=null;
			
			JOptionPane.showMessageDialog(null, "Congratulations! Your registration is complete ", "Info", JOptionPane.INFORMATION_MESSAGE);
			Login login= new Login();
			login.setVisible(true);
			setVisible(false);
		}
	}
}
