package trabalhoFinalLp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
public class CadastroLaboratorios extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    public CadastroLaboratorios() {
        getContentPane().setLayout(null);
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setTitle("Cadastro de Laboratórios");

        JLabel lblNovoLaboratorio = new JLabel("Preencha as informações do novo laboratório:");
        lblNovoLaboratorio.setBounds(27, 43, 295, 17);
        getContentPane().add(lblNovoLaboratorio);

        JLabel lblIdentificao = new JLabel("Identificação:");
        lblIdentificao.setBounds(27, 150, 99, 17);
        getContentPane().add(lblIdentificao);

        textField = new JTextField();
        textField.setBounds(120, 148, 83, 21);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblCapacidade = new JLabel("Capacidade:");
        lblCapacidade.setBounds(233, 150, 89, 17);
        getContentPane().add(lblCapacidade);

        textField_1 = new JTextField();
        textField_1.setBounds(314, 148, 99, 21);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblLocalizacao = new JLabel("Localização:");
        lblLocalizacao.setBounds(27, 179, 83, 17);
        getContentPane().add(lblLocalizacao);

        textField_2 = new JTextField();
        textField_2.setBounds(120, 179, 83, 21);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(233, 179, 60, 17);
        getContentPane().add(lblTipo);

        textField_3 = new JTextField();
        textField_3.setBounds(314, 179, 99, 21);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JLabel lblQuantidadeDeComputadores = new JLabel("Quantidade de computadores:");
        lblQuantidadeDeComputadores.setBounds(27, 208, 199, 17);
        getContentPane().add(lblQuantidadeDeComputadores);

        textField_4 = new JTextField();
        textField_4.setBounds(227, 206, 114, 21);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        JLabel lblImagem = new JLabel();
        lblImagem.setIcon(new ImageIcon("/home/nicolas/Downloads/lab.jpg"));
        lblImagem.setBounds(108, 60, 286, 78);
        getContentPane().add(lblImagem);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Laboratorio lab = new Laboratorio(
                        textField.getText(),
                        Integer.parseInt(textField_1.getText()),
                        textField_2.getText(),
                        textField_5.getText(),
                        textField_3.getText(),
                        Integer.parseInt(textField_4.getText())
                    );

                    ArrayList<Laboratorio> listaLabs = FileManager.loadLaboratorios();
                    listaLabs.add(lab);
                    FileManager.saveLaboratorios(listaLabs);

                    JOptionPane.showMessageDialog(null, "Laboratório cadastrado com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                        "Por favor, insira valores numéricos válidos para capacidade e computadores!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Erro ao cadastrar laboratório: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCadastrar.setBounds(120, 299, 106, 27);
        getContentPane().add(btnCadastrar);

        JLabel lblDescricao = new JLabel("Descrição breve:");
        lblDescricao.setBounds(27, 235, 99, 17);
        getContentPane().add(lblDescricao);

        textField_5 = new JTextField();
        textField_5.setBounds(132, 237, 281, 50);
        getContentPane().add(textField_5);
        textField_5.setColumns(10);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        btnCancelar.setBounds(261, 299, 106, 27);
        getContentPane().add(btnCancelar);
    }
}
