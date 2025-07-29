package trabalhoFinalLp2;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class FileManager {
    private static final String SALAS_FILE = "salas.txt";
    private static final String LABS_FILE = "laboratorios.txt";
    private static final String QUADRAS_FILE = "quadras.txt";
    private static final String REUNIOES_FILE = "reunioes.txt";

    private static File ensureFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo " + path + " criado com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo " + path + ": " + e.getMessage());
            }
        }
        return file;
    }

    private static void saveEspacos(ArrayList<? extends Espaço> espacos, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Espaço espaco : espacos) {
                writer.println(espacoToLine(espaco));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    public static void saveSalasAula(ArrayList<SalaAula> salas) {
        saveEspacos(salas, SALAS_FILE);
    }

    public static void saveLaboratorios(ArrayList<Laboratorio> labs) {
        saveEspacos(labs, LABS_FILE);
    }

    public static void saveQuadrasEsportivas(ArrayList<QuadraEsportiva> quadras) {
        saveEspacos(quadras, QUADRAS_FILE);
    }

    public static void saveSalasReuniao(ArrayList<SalaReuniao> reunioes) {
        saveEspacos(reunioes, REUNIOES_FILE);
    }

    private static String espacoToLine(Espaço espaco) {
    	StringBuilder sb = new StringBuilder();
        // Dados básicos
        sb.append(espaco.getIdentificacao()).append(";");
        sb.append(espaco.getCapacidade()).append(";");
        sb.append(espaco.getLocalizacao()).append(";");
        sb.append(espaco.getDescricao()).append(";");

        // Reservas (se houver)
        if (!espaco.getReservas().isEmpty()) {
            sb.append("[RESERVAS]");
            for (Reserva r : espaco.getReservas()) {
                sb.append(r.getUsuario()).append(",");
                sb.append(r.getInicio()).append(",");
                sb.append(r.getFim()).append(",");
                sb.append(r.getMotivo().replace(",", " ")).append("|;");
            }
        } else {
            sb.append(";"); // Campo vazio se não houver reservas
        }

        // Atributos específicos
        if (espaco instanceof SalaAula sala) {
            sb.append(sala.getpossuiQuadroBranco()).append(";");
            sb.append(sala.getpossuiProjetor()).append(";");
            sb.append(sala.getquantidadeLousas());
        } else if (espaco instanceof Laboratorio lab) {
            sb.append(lab.getTipoLaboratorio()).append(";");
            sb.append(lab.getQuantidadeComputadores());
        } else if (espaco instanceof QuadraEsportiva quadra) {
            sb.append(quadra.gettipoEsporte()).append(";");
            sb.append(quadra.getcoberta()).append(";");
            sb.append(quadra.getPossuiVestuário());
        } else if (espaco instanceof SalaReuniao reuniao) {
            sb.append(reuniao.getpossuiTv()).append(";");
            sb.append(reuniao.getpossuiWebconferencia()).append(";");
            sb.append(reuniao.getQuantidadeCadeiras());
        }

        return sb.toString();
    }

    private static void carregarReservas(Espaço espaco, String campoReservas) {
        if (campoReservas == null || campoReservas.isEmpty() || !campoReservas.startsWith("[RESERVAS]")) {
            return;
        }

        // Remove o marcador [RESERVAS]
        String reservasStr = campoReservas.substring(10);
        
        // Divide as reservas individuais
        String[] reservas = reservasStr.split("\\|");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (String reserva : reservas) {
            if (!reserva.trim().isEmpty()) {
                String[] dados = reserva.split(",");
                if (dados.length >= 4) {
                    try {
                        // Valida o formato das datas antes de criar a reserva
                        LocalDateTime.parse(dados[1].trim(), formatter);
                        LocalDateTime.parse(dados[2].trim(), formatter);
                        
                        Reserva r = new Reserva(
                            dados[0].trim(),  // usuário
                            dados[1].trim(),  // início
                            dados[2].trim(),  // fim
                            dados[3].trim()   // motivo
                        );
                        espaco.adicionarReserva(r);
                    } catch (DateTimeParseException e) {
                        System.err.println("Formato de data inválido na reserva: " + reserva);
                    } catch (Exception e) {
                        System.err.println("Erro ao processar reserva: " + reserva);
                    }
                }
            }
        }
    }

    public static ArrayList<SalaAula> loadSalasAula() {
        ArrayList<SalaAula> salas = new ArrayList<>();
        File file = ensureFile(SALAS_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                try {
                    String[] parts = line.split(";", -1);
                    if (parts.length < 8) {
                        System.err.println("Linha " + lineNum + " incompleta - esperados 8 campos, encontrados " + parts.length);
                        continue;
                    }

                    SalaAula sala = new SalaAula(
                        parts[0].isEmpty() ? "Sem identificação" : parts[0],
                        tryParseInt(parts[1], 10),
                        parts[2].isEmpty() ? "Sem localização" : parts[2],
                        parts[3].isEmpty() ? "Sem descrição" : parts[3],
                        Boolean.parseBoolean(parts[5]),
                        Boolean.parseBoolean(parts[6]),
                        tryParseInt(parts[7], 1)
                    );

                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        carregarReservas(sala, parts[4]);
                    }
                    salas.add(sala);
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha " + lineNum + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return salas;
    }

    // Método auxiliar para parse seguro de inteiros
    private static int tryParseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static ArrayList<Laboratorio> loadLaboratorios() {
        ArrayList<Laboratorio> labs = new ArrayList<>();
        File file = ensureFile(LABS_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1); // -1 para manter campos vazios
                if (parts.length >= 7) { // Campos obrigatórios
                    Laboratorio lab = new Laboratorio(
                        parts[0], 
                        Integer.parseInt(parts[1]), 
                        parts[2], 
                        parts[3],
                        parts[5], 
                        Integer.parseInt(parts[6])
                    );
                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        carregarReservas(lab, parts[4]);
                    }
                    labs.add(lab);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar laboratórios: " + e.getMessage());
        }
        return labs;
    }
    public static ArrayList<QuadraEsportiva> loadQuadrasEsportivas() {
        ArrayList<QuadraEsportiva> quadras = new ArrayList<>();
        File file = ensureFile(QUADRAS_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length >= 8) { // Campos obrigatórios
                    QuadraEsportiva quadra = new QuadraEsportiva(
                        parts[0], 
                        Integer.parseInt(parts[1]), 
                        parts[2], 
                        parts[3],
                        parts[5], 
                        Boolean.parseBoolean(parts[6]), 
                        Boolean.parseBoolean(parts[7])
                    );
                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        carregarReservas(quadra, parts[4]);
                    }
                    quadras.add(quadra);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar quadras: " + e.getMessage());
        }
        return quadras;
    }

    public static ArrayList<SalaReuniao> loadSalasReuniao() {
        ArrayList<SalaReuniao> reunioes = new ArrayList<>();
        File file = ensureFile(REUNIOES_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length >= 8) { // Campos obrigatórios
                    SalaReuniao reuniao = new SalaReuniao(
                        parts[0], 
                        Integer.parseInt(parts[1]), 
                        parts[2], 
                        parts[3],
                        Boolean.parseBoolean(parts[5]), 
                        Boolean.parseBoolean(parts[6]), 
                        Integer.parseInt(parts[7])
                    );
                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        carregarReservas(reuniao, parts[4]);
                    }
                    reunioes.add(reuniao);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar salas de reunião: " + e.getMessage());
        }
        return reunioes;
    }
}
