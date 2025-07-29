package trabalhoFinalLp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CadastroSalaReuniao extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    public CadastroSalaReuniao() {
        getContentPane().setLayout(null);
        setBackground(new Color(255, 255, 255));
        setSize(500, 400);
        setTitle("Cadastro de Sala de Reunião");

        JLabel lblCadastreAsInformacoes = new JLabel("Preencha as informações da nova sala de reunião:");
        lblCadastreAsInformacoes.setBounds(23, 32, 350, 17);
        getContentPane().add(lblCadastreAsInformacoes);

        JLabel lblIdentificacao = new JLabel("Identificação:");
        lblIdentificacao.setBounds(23, 117, 90, 17);
        getContentPane().add(lblIdentificacao);

        textField = new JTextField();
        textField.setBounds(115, 115, 114, 21);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblCapacidade = new JLabel("Capacidade:");
        lblCapacidade.setBounds(247, 117, 83, 17);
        getContentPane().add(lblCapacidade);

        textField_1 = new JTextField();
        textField_1.setBounds(324, 115, 114, 21);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JLabel lblLocalizacao = new JLabel("Localização:");
        lblLocalizacao.setBounds(23, 146, 90, 17);
        getContentPane().add(lblLocalizacao);

        textField_2 = new JTextField();
        textField_2.setBounds(115, 148, 114, 21);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        JLabel lblQuantidadeDeCadeiras = new JLabel("Quantidade de cadeiras:");
        lblQuantidadeDeCadeiras.setBounds(247, 146, 145, 17);
        getContentPane().add(lblQuantidadeDeCadeiras);

        textField_3 = new JTextField();
        textField_3.setBounds(397, 148, 41, 21);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JCheckBox chckbxTelevisao = new JCheckBox("Televisão");
        chckbxTelevisao.setBounds(23, 171, 115, 25);
        getContentPane().add(chckbxTelevisao);

        JCheckBox chckbxWebconferencia = new JCheckBox("Suporte Webconferência");
        chckbxWebconferencia.setBounds(141, 171, 180, 25);
        getContentPane().add(chckbxWebconferencia);

        JLabel lblDescricaoBreve = new JLabel("Descrição breve:");
        lblDescricaoBreve.setBounds(23, 208, 104, 17);
        getContentPane().add(lblDescricaoBreve);

        textField_4 = new JTextField();
        textField_4.setBounds(134, 206, 213, 33);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(107, 251, 106, 27);
        btnCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    SalaReuniao reuniao = new SalaReuniao(
                        textField.getText(),
                        Integer.parseInt(textField_1.getText()),
                        textField_2.getText(),
                        textField_4.getText(),
                        chckbxTelevisao.isSelected(),
                        chckbxWebconferencia.isSelected(),
                        Integer.parseInt(textField_3.getText())
                    );

                    ArrayList<SalaReuniao> listaReunioes = FileManager.loadSalasReuniao();
                    listaReunioes.add(reuniao);
                    FileManager.saveSalasReuniao(listaReunioes);

                    JOptionPane.showMessageDialog(null, "Sala de reunião cadastrada com sucesso!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos para capacidade e cadeiras!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar sala de reunião: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        getContentPane().add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(267, 251, 106, 27);
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnCancelar);
    }
}
