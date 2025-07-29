package trabalhoFinalLp2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import java.util.*;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UserNameField;
	private JPasswordField passwordField;
	HashMap<String,String>loginInfo=new HashMap<String,String>();
	ArrayList<String> Admins = new ArrayList<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ID_Senhas idSenhas = new ID_Senhas();
					Login frame = new Login(idSenhas.getLoginInfo(),idSenhas.getAdmins());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login(HashMap<String,String> idSenhas,ArrayList<String>admins) {
		loginInfo = idSenhas;
		Admins=admins;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 51, 51));
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("SGEA :  Sistema de Gestão de Espaços Acadêmicos");
		Title.setBounds(25, 33, 307, 17);
		contentPane.add(Title);
		
		JLabel lblLoginSucesso = new JLabel("");
		lblLoginSucesso.setForeground(Color.gray);
		lblLoginSucesso.setBounds(104, 235, 247, 17);
		contentPane.add(lblLoginSucesso);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(37, 84, 60, 17);
		contentPane.add(lblLogin);
		
		UserNameField = new JTextField();
		UserNameField.setBounds(145, 142, 163, 21);
		contentPane.add(UserNameField);
		UserNameField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(61, 144, 81, 17);
		contentPane.add(lblUsername);
		
		passwordField = new JPasswordField();
		
		passwordField.setBounds(145, 175, 163, 21);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(59, 173, 68, 17);
		contentPane.add(lblPassword);
		
		JLabel lblEntreSeuNome = new JLabel("Entre seu nome de usuário e senha cadastrado");
		lblEntreSeuNome.setBounds(37, 113, 295, 17);
		contentPane.add(lblEntreSeuNome);
		
		JButton btnEnter = new JButton("Login");
		btnEnter.setForeground(new Color(255, 255, 255));
		btnEnter.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userID=UserNameField.getText();
				String password =String.valueOf(passwordField.getPassword());
				boolean isAdm=false;
				if(loginInfo.containsKey(userID)) {
					if(loginInfo.get(userID).equals(password)) {
						lblLoginSucesso.setForeground(Color.green);
						lblLoginSucesso.setText("Login sucedido");
						if(Admins.contains(userID)) {
							isAdm=true;
						}
						UserNameField.setText("");
						passwordField.setText("");
						
						MainMenu Menu = new MainMenu(userID,isAdm);
						Menu.setVisible(true);
						
					}else {
						lblLoginSucesso.setForeground(Color.red);
						lblLoginSucesso.setText("Senha errada");
						UserNameField.setText("");
						passwordField.setText("");
					}
					
				}else {
					lblLoginSucesso.setForeground(Color.red);
					lblLoginSucesso.setText("Nenhum usuário com esse nome");
					UserNameField.setText("");
					passwordField.setText("");
				}
			}
		});
		btnEnter.setBackground(new Color(224, 27, 36));
		btnEnter.setBounds(94, 208, 106, 27);
		contentPane.add(btnEnter);
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setBackground(new Color(224, 27, 36));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignIn signIn = new SignIn(new ID_Senhas());
	            signIn.setVisible(true);
				
			}
		});
		btnSignIn.setBounds(245, 208, 106, 27);
		contentPane.add(btnSignIn);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/home/nicolas/Downloads/teto_1_60x58.jpg"));
		lblNewLabel.setBounds(357, 33, 60, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblencareAUniversidadeconstrua = new JLabel("\"that machine really can love\"-teto");
		lblencareAUniversidadeconstrua.setBounds(35, 55, 382, 17);
		contentPane.add(lblencareAUniversidadeconstrua);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setBounds(12, 177, 60, 17);
		contentPane.add(lblNewLabel_1);
		
		

	}
}
