package trabalhoFinalLp2;

public class SalaReuniao extends Espaço {
	private boolean possuiTV;
    private boolean possuiWebconferencia;
    private int quantidadeCadeiras;

    public SalaReuniao(String identificacao, int capacidade, String localizacao, String descricao,
                      boolean possuiTV, boolean possuiWebconferencia, int quantidadeCadeiras) {
        super(identificacao, capacidade, localizacao, descricao);
        this.possuiTV = possuiTV;
        this.possuiWebconferencia = possuiWebconferencia;
        this.quantidadeCadeiras = quantidadeCadeiras;
    }
    public void setpossuiTv(boolean possui) {
    	this.possuiTV=possui;
    }
    public boolean getpossuiTv() {
    	return this.possuiTV;
    }
    public void setpossuiWebconferencia(boolean possui) {
    	this.possuiWebconferencia=possui;
    }
    public boolean getpossuiWebconferencia() {
    	return this.possuiWebconferencia;
    }
    public void setQuantidadeCadeiras(int quantidadeCadeiras) {
    	this.quantidadeCadeiras=quantidadeCadeiras;
    }
    public int getQuantidadeCadeiras() {
    	return this.quantidadeCadeiras;
    }
}