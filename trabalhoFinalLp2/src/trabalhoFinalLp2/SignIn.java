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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class SignIn extends JFrame {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField adminCodeField;
    private ID_Senhas idSenhas;
	public SignIn(ID_Senhas idSenhas){
		this.idSenhas = idSenhas;
		getContentPane().setLayout(null);
		setSize(500, 400);
		usernameField = new JTextField();
		usernameField.setBounds(146, 134, 173, 21);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblBoasVindasNovo = new JLabel("Boas vindas novo usuário");
		lblBoasVindasNovo.setBounds(47, 87, 199, 17);
		getContentPane().add(lblBoasVindasNovo);
		
		JLabel lblDigiteSeuNome = new JLabel("Digite seu nome de usuário e senha:");
		lblDigiteSeuNome.setBounds(47, 105, 221, 17);
		getContentPane().add(lblDigiteSeuNome);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 167, 173, 21);
		getContentPane().add(passwordField);
		
		adminCodeField = new JTextField();
		adminCodeField.setBounds(301, 218, 114, 21);
		getContentPane().add(adminCodeField);
		adminCodeField.setColumns(10);
		
		JLabel lblCasoSejaUm = new JLabel("Caso seja um novo admin digite o código:");
		lblCasoSejaUm.setBounds(47, 220, 254, 17);
		getContentPane().add(lblCasoSejaUm);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setBounds(70, 136, 100, 17);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(77, 169, 60, 17);
		getContentPane().add(lblPassword);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener((e->registerUser()));
		btnNewButton.setBounds(184, 200, 84, 17);
		getContentPane().add(btnNewButton);
	}
	private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String adminCode = adminCodeField.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isAdmin = adminCode.equals("banana"); 
        
        if (idSenhas.getLoginInfo().containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        idSenhas.addUser(username, password, isAdmin);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        dispose();
    }
	
}
