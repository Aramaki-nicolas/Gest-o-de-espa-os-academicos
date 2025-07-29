package trabalhoFinalLp2;

public class QuadraEsportiva extends Espaço {
	private String tipoEsporte;
    private boolean coberta;
    private boolean possuiVestuario;

    public QuadraEsportiva(String identificacao, int capacidade, String localizacao, String descricao,
                          String tipoEsporte, boolean coberta, boolean possuiVestuario) {
        super(identificacao, capacidade, localizacao, descricao);
        this.tipoEsporte = tipoEsporte;
        this.coberta = coberta;
        this.possuiVestuario = possuiVestuario;
    }

    public void settipoEsporte(String esporte) {
    	this.tipoEsporte=esporte;
    }
    public String gettipoEsporte() {
    	return this.tipoEsporte;
    }
    public void setcoberta(boolean coberta) {
    	this.coberta=coberta;
    }
    public boolean getcoberta() {
    	return coberta;
    }
    public void setPossuivestuario(boolean possui) {
    	this.possuiVestuario=possui;
    }
    public boolean getPossuiVestuário() {
    	return this.possuiVestuario;
    }
 
}