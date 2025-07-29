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
import java.util.HashMap;
import java.awt.Font;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MainMenu extends JFrame {
	public MainMenu(String username,boolean isAdmin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu");
		setBackground(new Color(255, 255, 255));
		setSize(600, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		JLabel lblSgeaSistema = new JLabel("SGEA :  Sistema de Gestão de Espaços Acadêmicos");
		lblSgeaSistema.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSgeaSistema.setBounds(12, 12, 575, 62);
		getContentPane().add(lblSgeaSistema);
		ListEspacosCadastrados Espacos = new ListEspacosCadastrados();
		JButton btnCadastrarSala = new JButton("Cadastrar espaços");
		btnCadastrarSala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isAdmin) {
					CadastroEspacos cadastro= new CadastroEspacos(Espacos);
					cadastro.setVisible(true);
				}
				
			}
		});
		btnCadastrarSala.setBounds(399, 128, 170, 27);
		getContentPane().add(btnCadastrarSala);
		
		JButton btnVerificarEspacos = new JButton("Verificar espacos");
		btnVerificarEspacos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				VerEspacos espacos = new VerEspacos();
				espacos.setVisible(true);
			}
		});
		btnVerificarEspacos.setBounds(399, 177, 170, 27);
		getContentPane().add(btnVerificarEspacos);
		
		JButton btnEncerrarSessao = new JButton("Encerrar Sessao");
		btnEncerrarSessao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnEncerrarSessao.setBounds(399, 275, 170, 27);
		getContentPane().add(btnEncerrarSessao);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/home/nicolas/Downloads/8667e965-5e9c-4baa-b4fc-0b8ad45f8737_4_384x223.jpeg"));
		lblNewLabel.setBounds(42, 128, 318, 223);
		getContentPane().add(lblNewLabel);
		JLabel lblBemVindo = new JLabel("Bem vindo  admin," + username);
		if(isAdmin) {
			lblBemVindo.setText("Bem vindo  admin," + username);
		}else {
			lblBemVindo.setText("Bem vindo, "+username);
		}
		
		lblBemVindo.setBounds(22, 61, 362, 17);
		getContentPane().add(lblBemVindo);
		
		JButton btnNewButton = new JButton("Reservar espaços");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ListEspacosCadastrados lista = new ListEspacosCadastrados();
	            new ReservaEspaco(lista, username).setVisible(true);
				
			}
		});
		btnNewButton.setBounds(399, 226, 170, 27);
		getContentPane().add(btnNewButton);
	}
}
