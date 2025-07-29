package trabalhoFinalLp2;

public class SalaAula extends Espaço {
	private boolean possuiQuadroBranco;
    private boolean possuiProjetor;
    private int quantidadeLousas;
    
    public SalaAula(String identificacao, int capacidade, String localizacao, String descricao,
                   boolean possuiQuadroBranco, boolean possuiProjetor, int quantidadeLousas) {
        super(identificacao, capacidade, localizacao, descricao);
        this.possuiQuadroBranco = possuiQuadroBranco;
        this.possuiProjetor = possuiProjetor;
        this.quantidadeLousas = quantidadeLousas;
    }
    public void setPossuiQuadroBranco(boolean possui) {
    	this.possuiQuadroBranco=possui;
    }
    public boolean getpossuiQuadroBranco() {
    	return this.possuiQuadroBranco;
    }
    public void setpossuiProjetor(boolean possui) {
    	this.possuiProjetor=possui;
    }
    public boolean getpossuiProjetor() {
    	return this.possuiProjetor;
    }
    public void setquantidadeLousas(int quant) {
    	this.quantidadeLousas=quant;
    }
    public int getquantidadeLousas() {
    	return this.quantidadeLousas;
    }
}
