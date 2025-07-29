package trabalhoFinalLp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CadastroQuadraEsportiva extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_5;
    private JCheckBox chckbxCoberta;
    private JCheckBox chckbxVestiario;

    public CadastroQuadraEsportiva() {
        getContentPane().setLayout(null);
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setTitle("Cadastro de Quadra Esportiva");

        JLabel lblIdentificao = new JLabel("Identificação:");
        lblIdentificao.setBounds(27, 150, 99, 17);
        getContentPane().add(lblIdentificao);

        textField = new JTextField();
        textField.setBounds(120, 148, 83, 21);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblCapacidade = new JLabel("Capacidade:");
        lblCapacidade.setBounds(262, 150, 89, 17);
        getContentPane().add(lblCapacidade);

        textField_1 = new JTextField();
        textField_1.setBounds(340, 148, 99, 21);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblLocalizacao = new JLabel("Localização:");
        lblLocalizacao.setBounds(27, 179, 83, 17);
        getContentPane().add(lblLocalizacao);

        textField_2 = new JTextField();
        textField_2.setBounds(120, 179, 83, 21);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblEsportesSuportados = new JLabel("Esportes suportados:");
        lblEsportesSuportados.setBounds(209, 183, 132, 17);
        getContentPane().add(lblEsportesSuportados);

        textField_5 = new JTextField();
        textField_5.setBounds(340, 179, 99, 21);
        getContentPane().add(textField_5);
        textField_5.setColumns(10);

        chckbxCoberta = new JCheckBox("Coberta");
        chckbxCoberta.setBounds(25, 208, 115, 25);
        getContentPane().add(chckbxCoberta);

        chckbxVestiario = new JCheckBox("Vestiário");
        chckbxVestiario.setBounds(140, 208, 115, 25);
        getContentPane().add(chckbxVestiario);

        JLabel lblDescricaoBreve = new JLabel("Descrição breve:");
        lblDescricaoBreve.setBounds(27, 241, 125, 17);
        getContentPane().add(lblDescricaoBreve);

        textField_3 = new JTextField();
        textField_3.setBounds(140, 239, 206, 59);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JLabel lblTitulo = new JLabel("Preencha as informações da nova quadra esportiva");
        lblTitulo.setBounds(27, 46, 350, 17);
        getContentPane().add(lblTitulo);

        JLabel lblImagem = new JLabel();
        lblImagem.setIcon(new ImageIcon("/home/nicolas/Downloads/vantagens-da-quadra-poliesportiva_optimized.png"));
        lblImagem.setBounds(150, 64, 191, 72);
        getContentPane().add(lblImagem);

        JButton btnCriar = new JButton("Cadastrar");
        btnCriar.setBounds(120, 309, 106, 27);
        btnCriar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    QuadraEsportiva quadra = new QuadraEsportiva(
                        textField.getText(),
                        Integer.parseInt(textField_1.getText()),
                        textField_2.getText(),
                        textField_3.getText(),
                        textField_5.getText(),
                        chckbxCoberta.isSelected(),
                        chckbxVestiario.isSelected()
                    );

                    ArrayList<QuadraEsportiva> listaQuadras = FileManager.loadQuadrasEsportivas();
                    listaQuadras.add(quadra);
                    FileManager.saveQuadrasEsportivas(listaQuadras);

                    JOptionPane.showMessageDialog(null, "Quadra esportiva cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido para capacidade!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar quadra: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(btnCriar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(259, 309, 106, 27);
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnCancelar);
    }
}