package trabalhoFinalLp2;
import java.util.*;
public class Laboratorio extends Espaço {
	private String tipoLaboratorio;
    private int quantidadeComputadores;
    
    public Laboratorio(String identificacao, int capacidade, String localizacao, String descricao,
                      String tipoLaboratorio, int quantidadeComputadores) {
        super(identificacao, capacidade, localizacao, descricao);
        this.tipoLaboratorio = tipoLaboratorio;
        this.quantidadeComputadores = quantidadeComputadores;
    }

    public void setTipoLaboratorio(String tipo) {
    	this.tipoLaboratorio=tipo;
    }
    public String getTipoLaboratorio() {
    	return this.tipoLaboratorio;
    }
    public void setQuantidadeComputadores(int quant) {
    	this.quantidadeComputadores=quant;
    }
    public int getQuantidadeComputadores() {
    	return this.quantidadeComputadores;
    }
}
