package trabalhoFinalLp2;
import java.util.*;

public class ListEspacosCadastrados {
    ArrayList<SalaAula> SalaAulaCadastrados;
    ArrayList<Laboratorio> LaboratorioCadastrados;
    ArrayList<QuadraEsportiva> QuadraEsportivaCadastradas;
    ArrayList<SalaReuniao> SalaReuniaoCadastradas;
    
    public ListEspacosCadastrados(){
        // Carregar dados dos arquivos ao inicializar
    	this.SalaAulaCadastrados = new ArrayList<>();
        this.LaboratorioCadastrados = new ArrayList<>();
        this.QuadraEsportivaCadastradas = new ArrayList<>();
        this.SalaReuniaoCadastradas = new ArrayList<>();
        carregarDados();
    }
    private void carregarDados() {
        this.SalaAulaCadastrados = FileManager.loadSalasAula();
        this.LaboratorioCadastrados = FileManager.loadLaboratorios();
        this.QuadraEsportivaCadastradas = FileManager.loadQuadrasEsportivas();
        this.SalaReuniaoCadastradas = FileManager.loadSalasReuniao();
    }
    // Métodos para adicionar espaços e salvar automaticamente
    public void addSalaAula(SalaAula sala) {
        SalaAulaCadastrados.add(sala);
        FileManager.saveSalasAula(SalaAulaCadastrados);
    }
    
    public void addLaboratorio(Laboratorio lab) {
        LaboratorioCadastrados.add(lab);
        FileManager.saveLaboratorios(LaboratorioCadastrados);
    }
    
    public void addQuadraEsportiva(QuadraEsportiva quadra) {
        QuadraEsportivaCadastradas.add(quadra);
        FileManager.saveQuadrasEsportivas(QuadraEsportivaCadastradas);
    }
    
    public void addSalaReuniao(SalaReuniao reuniao) {
        SalaReuniaoCadastradas.add(reuniao);
        FileManager.saveSalasReuniao(SalaReuniaoCadastradas);
    }
    
    // Getters para as listas
    public ArrayList<SalaAula> getSalasAula() {
        return SalaAulaCadastrados;
    }
    
    public ArrayList<Laboratorio> getLaboratorios() {
        return LaboratorioCadastrados;
    }
    
    public ArrayList<QuadraEsportiva> getQuadrasEsportivas() {
        return QuadraEsportivaCadastradas;
    }
    
    public ArrayList<SalaReuniao> getSalasReuniao() {
        return SalaReuniaoCadastradas;
    }
    public String getResumoQuantidades() {
        return String.format(
            "Salas de Aula: %d | Laboratórios: %d | Quadras: %d | Salas de Reunião: %d",
            getSalasAula().size(),
            getLaboratorios().size(),
            getQuadrasEsportivas().size(),
            getSalasReuniao().size()
        );
    }
}
    
