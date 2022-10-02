package com.Jo.hotel.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import com.Jo.hotel.controller.UserController;
import com.Jo.hotel.modelo.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Login extends JFrame {
	private JTextField inputUserId;
	private JPasswordField inputUserPass;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Login() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.PINK);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(134, 142, 152, 35);
		getContentPane().add(panel_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 LogIn();
			}
		});
		panel_1.add(btnNewButton);
		
		inputUserPass = new JPasswordField();
		inputUserPass.setBackground(Color.LIGHT_GRAY);
		inputUserPass.setBounds(200, 102, 86, 20);
		getContentPane().add(inputUserPass);
		
		inputUserId = new JTextField();
		inputUserId.setBounds(200, 54, 86, 20);
		getContentPane().add(inputUserId);
		inputUserId.setBackground(Color.LIGHT_GRAY);
		inputUserId.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBounds(363, 226, 61, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("*");
		btnNewButton_2.setBounds(18, 226, 39, 24);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Create Account");
		lblNewLabel_3.setBounds(67, 230, 108, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("userID:");
		lblNewLabel_1.setBounds(137, 57, 60, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pass:");
		lblNewLabel_2.setBounds(145, 105, 45, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblHotel = new JLabel("HOTEL");
		lblHotel.setFont(new Font("Sitka Text", Font.BOLD, 18));
		lblHotel.setBounds(174, 11, 77, 32);
		getContentPane().add(lblHotel);
		
		JLabel label_img = new JLabel("New label");
		label_img.setIcon(new ImageIcon(Login.class.getResource("/com/Jo/hotel/img/Building.png")));
		label_img.setBounds(0, 0, 434, 261);
		getContentPane().add(label_img);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccount nuevaCuenta= new CreateAccount();
				nuevaCuenta.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	
public void validateLogin(String userId,String userPass) {
		UserController clienteLog= new UserController();
		List <User> admins=	clienteLog.getLogIn(userId,userPass);
		clienteLog=null;
		
		if(admins.size()==0) {
			 JOptionPane.showMessageDialog(null, "Unknown User ", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		
		admins.forEach(admin ->{
			 if(admin.getJerarciaId()==1) { 
				 AccountAdmin accountAdminFrame= new AccountAdmin(admin);
				 accountAdminFrame.setVisible(true);
				 setVisible(false);	
			 }
			 else {
				 if(admin.getJerarciaId()==2) {
					 AccountClient accountClientFrame= new AccountClient(admin);
					 accountClientFrame.setVisible(true);	
					 setVisible(false);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "Something went wrong", "Info", JOptionPane.INFORMATION_MESSAGE);
				 } 
			 }
			});
	}
	
	
	public void LogIn() {
		var userId=inputUserId.getText();
		var userPass=inputUserPass.getText();
		
		if(userId.isEmpty()|| userPass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "ID or Pass not found", "Info", JOptionPane.INFORMATION_MESSAGE);	
		}
		else {
			validateLogin(userId,userPass);
		}
	}
}
