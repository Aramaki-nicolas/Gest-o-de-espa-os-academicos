package trabalhoFinalLp2;

import java.util.ArrayList;
import java.util.List;
public abstract class Espaço {
	private String identificacao;
    private int capacidade;
    private String localizacao;
    private String descricao;
    private List<Reserva> reservas;

    public Espaço(String identificacao, int capacidade, String localizacao, String descricao) {
        this.identificacao = identificacao;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.reservas = new ArrayList<>();
    }

	public boolean adicionarReserva(Reserva reserva) {
        if (estaDisponivel(reserva.getInicio(), reserva.getFim())) {
            reservas.add(reserva);
            return true;
        }
        return false;
    }
	public boolean estaDisponivel(String inicio, String fim) {
        for (Reserva r : reservas) {
            if (r.conflitaCom(inicio, fim)) {
                return false;
            }
        }
        return true;
    }
	public boolean removerReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }
	
	public String getReservasAsString() {
        StringBuilder sb = new StringBuilder();
        for (Reserva r : reservas) {
            sb.append(r.toString()).append("\n");
        }
        return sb.toString();
    }
	public ArrayList<Reserva> getReservas() {
        return new ArrayList<>(reservas); // Retorna cópia para segurança
    }
    public String getIdentificacao() { return identificacao; }
    public int getCapacidade() { return capacidade; }
    public String getLocalizacao() { return localizacao; }
    public String getDescricao() { return descricao; }
    
   
    
    

    @Override
    public String toString() {
        return String.format(
            "Identificação: %s | Capacidade: %d | Localização: %s | Descrição: %s | Disponível: %s",
            identificacao,
            capacidade,
            localizacao,
            descricao,
            estaDisponivel("", "") ? "Sim" : "Não"  
        );
    }
}
