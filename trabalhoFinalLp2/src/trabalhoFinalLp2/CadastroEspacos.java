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
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
public class CadastroEspacos extends JFrame {
	private JTextField QuantCadeirasTextField;
	private JTextField textField_4;
	public CadastroEspacos(ListEspacosCadastrados List) {
		getContentPane().setEnabled(false);
		getContentPane().setLayout(null);
		setSize(600,200);
		JLabel lblCadasrtroDeEspaos = new JLabel("Cadasrtro de espaços");
		lblCadasrtroDeEspaos.setFont(new Font("Dialog", Font.BOLD, 22));
		lblCadasrtroDeEspaos.setBounds(28, 33, 243, 25);
		getContentPane().add(lblCadasrtroDeEspaos);
		
		JButton buttonSalaDeAula = new JButton("Sala de aula");
		buttonSalaDeAula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroSalas cadasSalasAula = new CadastroSalas();
				cadasSalasAula.setVisible(true);
				dispose();
			}
		});
		
		buttonSalaDeAula.setBounds(28, 99, 106, 27);
		getContentPane().add(buttonSalaDeAula);
		
		JButton btnNewButton_1 = new JButton("Sala de Reuniões");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroSalaReuniao cadasSalaReuniao = new CadastroSalaReuniao();
				cadasSalaReuniao.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(146, 99, 141, 27);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Laboratorio");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroLaboratorios CadastroLab=new CadastroLaboratorios();
				CadastroLab.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(299, 99, 106, 27);
		getContentPane().add(btnNewButton_2);
		
		JButton btnQuadraEsportiva = new JButton("Quadra Esportiva");
		btnQuadraEsportiva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroQuadraEsportiva cadasQuadra = new CadastroQuadraEsportiva();
				cadasQuadra.setVisible(true);
				dispose();
			}
		});
		btnQuadraEsportiva.setBounds(417, 99, 150, 27);
		getContentPane().add(btnQuadraEsportiva);
		
		JLabel lblSelecioneOTipo = new JLabel("Selecione o tipo de espaço a ser cadastrado para selecionar as opções especificas;");
		lblSelecioneOTipo.setBounds(28, 70, 508, 17);
		getContentPane().add(lblSelecioneOTipo);
		
		
		
		
		
		
	}
}
