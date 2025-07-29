package trabalhoFinalLp2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VerEspacos extends JFrame {
    public VerEspacos() {
        setTitle("Espaços Cadastrados");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Aba de Salas de Aula
        tabbedPane.addTab("Salas de Aula", criarPainelEspaco(
            FileManager.loadSalasAula(),
            "Nenhuma sala de aula cadastrada."
        ));

        // Aba de Laboratórios
        tabbedPane.addTab("Laboratórios", criarPainelEspaco(
            FileManager.loadLaboratorios(),
            "Nenhum laboratório cadastrado."
        ));

        // Aba de Quadras Esportivas
        tabbedPane.addTab("Quadras Esportivas", criarPainelEspaco(
            FileManager.loadQuadrasEsportivas(),
            "Nenhuma quadra esportiva cadastrada."
        ));

        // Aba de Salas de Reunião
        tabbedPane.addTab("Salas de Reunião", criarPainelEspaco(
            FileManager.loadSalasReuniao(),
            "Nenhuma sala de reunião cadastrada."
        ));

        add(tabbedPane);
    }

    private <T extends Espaço> JPanel criarPainelEspaco(ArrayList<T> espacos, String mensagemVazio) {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        if (espacos == null || espacos.isEmpty()) {
            textArea.setText(mensagemVazio);
        } else {
            StringBuilder sb = new StringBuilder();
            for (T espaco : espacos) {
                sb.append(espaco.toString()).append("\n\n");
            }
            textArea.setText(sb.toString());
        }
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return panel;
    }
}
