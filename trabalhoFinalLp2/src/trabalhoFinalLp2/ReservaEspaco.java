package trabalhoFinalLp2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ReservaEspaco extends JFrame {
    private JComboBox<Espaço> comboEspacos;
    private JTextField txtInicio;
    private JTextField txtFim;
    private JTextField txtMotivo;
    private JButton btnVerificar;
    private JButton btnReservar;
    private JTextArea txtResultado;
    private String usuarioAtual;

    public ReservaEspaco(ListEspacosCadastrados lista, String usuario) {
        this.usuarioAtual = usuario;
        initializeUI(lista);
    }

    private void initializeUI(ListEspacosCadastrados lista) {
        setTitle("Sistema de Reservas");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));
        
        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        
        // Componentes
        formPanel.add(new JLabel("Espaço:"));
        comboEspacos = new JComboBox<>();
        carregarEspacos(lista);
        formPanel.add(comboEspacos);
        
        formPanel.add(new JLabel("Data/Hora Início (dd/MM/yyyy HH:mm):"));
        txtInicio = new JTextField();
        formPanel.add(txtInicio);
        
        formPanel.add(new JLabel("Data/Hora Fim (dd/MM/yyyy HH:mm):"));
        txtFim = new JTextField();
        formPanel.add(txtFim);
        
        formPanel.add(new JLabel("Motivo:"));
        txtMotivo = new JTextField();
        formPanel.add(txtMotivo);
        
        // Painel de botões
        JPanel buttonPanel = new JPanel();
        btnVerificar = new JButton("Verificar Disponibilidade");
        btnReservar = new JButton("Fazer Reserva");
        buttonPanel.add(btnVerificar);
        buttonPanel.add(btnReservar);
        
        // Área de resultados
        txtResultado = new JTextArea(10, 40);
        txtResultado.setEditable(false);
        
        // Listeners
        btnVerificar.addActionListener(e -> verificarDisponibilidade());
        btnReservar.addActionListener(e -> fazerReserva(lista));
        
        // Adicionar componentes ao frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(txtResultado), BorderLayout.SOUTH);
    }

    private void carregarEspacos(ListEspacosCadastrados lista) {
        comboEspacos.removeAllItems();
        for (SalaAula sala : lista.getSalasAula()) {
            comboEspacos.addItem(sala);
        }
        for (Laboratorio lab : lista.getLaboratorios()) {
            comboEspacos.addItem(lab);
        }
        for (QuadraEsportiva quadra:lista.getQuadrasEsportivas()) {
        	comboEspacos.addItem(quadra);
        }
        for (SalaReuniao sala : lista.getSalasReuniao()) {
        	comboEspacos.addItem(sala);
        }
    }

    private void verificarDisponibilidade() {
        Espaço espaco = (Espaço) comboEspacos.getSelectedItem();
        String inicio = txtInicio.getText();
        String fim = txtFim.getText();
        
        if (espaco == null || inicio.isEmpty() || fim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        
        if (espaco.estaDisponivel(inicio, fim)) {
            txtResultado.setText("Espaço disponível no período selecionado!");
        } else {
            txtResultado.setText("Espaço INDISPONÍVEL no período selecionado.\n");
            txtResultado.append("Reservas existentes:\n");
            for (Reserva r : espaco.getReservas()) {
                if (r.conflitaCom(inicio, fim)) {
                    txtResultado.append(r.toString());
                    txtResultado.append("\n\n");
                }
            }
        }
    }

    private void fazerReserva(ListEspacosCadastrados lista) {
        Espaço espaco = (Espaço) comboEspacos.getSelectedItem();
        String inicio = txtInicio.getText();
        String fim = txtFim.getText();
        String motivo = txtMotivo.getText();
        
        if (espaco == null || inicio.isEmpty() || fim.isEmpty() || motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        
        Reserva novaReserva = new Reserva(usuarioAtual, inicio, fim, motivo);
        
        if (espaco.adicionarReserva(novaReserva)) {
            txtResultado.setText("Reserva realizada com sucesso!\n" + novaReserva);
            // Atualizar arquivos
            FileManager.saveSalasAula(lista.getSalasAula());
            FileManager.saveLaboratorios(lista.getLaboratorios());
            FileManager.saveQuadrasEsportivas(lista.getQuadrasEsportivas());
            FileManager.saveSalasReuniao(lista.getSalasReuniao());
        } else {
            txtResultado.setText("Não foi possível realizar a reserva. Conflito de horário.");
        }
    }
}