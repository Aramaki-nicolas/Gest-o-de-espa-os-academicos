package trabalhoFinalLp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CadastroSalas extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public CadastroSalas() {
        getContentPane().setLayout(null);
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setTitle("Cadastro de Sala de Aula");

        JLabel lblPreenchaAsCaracteristicas = new JLabel("Preencha as características da nova sala de aula:");
        lblPreenchaAsCaracteristicas.setBounds(22, 30, 350, 17);
        getContentPane().add(lblPreenchaAsCaracteristicas);

        JLabel lblIdentificacao = new JLabel("Identificação:");
        lblIdentificacao.setBounds(22, 111, 178, 17);
        getContentPane().add(lblIdentificacao);

        textField = new JTextField();
        textField.setBounds(109, 109, 114, 21);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblCapacidade = new JLabel("Capacidade:");
        lblCapacidade.setBounds(241, 111, 89, 17);
        getContentPane().add(lblCapacidade);

        textField_1 = new JTextField();
        textField_1.setBounds(324, 109, 114, 21);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblLocalizacao = new JLabel("Localização:");
        lblLocalizacao.setBounds(22, 140, 89, 17);
        getContentPane().add(lblLocalizacao);

        textField_2 = new JTextField();
        textField_2.setBounds(109, 140, 114, 21);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblQuantidadeDeLousas = new JLabel("Quantidade de lousas:");
        lblQuantidadeDeLousas.setBounds(241, 140, 154, 17);
        getContentPane().add(lblQuantidadeDeLousas);

        textField_3 = new JTextField();
        textField_3.setBounds(380, 138, 25, 21);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JCheckBox chckbxQuadroBranco = new JCheckBox("Quadro branco");
        chckbxQuadroBranco.setBounds(22, 165, 115, 25);
        getContentPane().add(chckbxQuadroBranco);

        JCheckBox chckbxProjetor = new JCheckBox("Projetor");
        chckbxProjetor.setBounds(141, 165, 115, 25);
        getContentPane().add(chckbxProjetor);

        JLabel lblDescricaoBreve = new JLabel("Descrição breve:");
        lblDescricaoBreve.setBounds(22, 195, 115, 17);
        getContentPane().add(lblDescricaoBreve);

        textField_4 = new JTextField();
        textField_4.setBounds(127, 193, 208, 45);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(94, 243, 106, 27);
        btnCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SalaAula sala = new SalaAula(
                        textField.getText(),
                        Integer.parseInt(textField_1.getText()),
                        textField_2.getText(),
                        textField_4.getText(),
                        chckbxQuadroBranco.isSelected(),
                        chckbxProjetor.isSelected(),
                        Integer.parseInt(textField_3.getText())
                    );

                    ArrayList<SalaAula> listaSalas = FileManager.loadSalasAula();
                    listaSalas.add(sala);
                    FileManager.saveSalasAula(listaSalas);

                    // Verificação
                    ArrayList<SalaAula> salasCarregadas = FileManager.loadSalasAula();
                    System.out.println("Salas após cadastro: " + salasCarregadas.size());
                    
                    JOptionPane.showMessageDialog(null, "Sala cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valores inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(241, 243, 106, 27);
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnCancelar);
    }
}

