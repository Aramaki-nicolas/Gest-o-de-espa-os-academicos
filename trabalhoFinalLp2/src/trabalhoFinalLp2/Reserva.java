package trabalhoFinalLp2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Reserva {
    private String usuario;
    private String inicio;
    private String fim;
    private String motivo;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Reserva(String usuario, String inicio, String fim, String motivo) {
        this.usuario = usuario;
        this.inicio = inicio;
        this.fim = fim;
        this.motivo = motivo;
    }
    
    public boolean conflitaCom(String outroInicio, String outroFim) {
        try {
            LocalDateTime ini1 = LocalDateTime.parse(inicio, formatter);
            LocalDateTime fim1 = LocalDateTime.parse(fim, formatter);
            LocalDateTime ini2 = LocalDateTime.parse(outroInicio, formatter);
            LocalDateTime fim2 = LocalDateTime.parse(outroFim, formatter);
            
            return !(fim1.isBefore(ini2) || ini1.isAfter(fim2));
        } catch (DateTimeParseException e) {
            return true; // Se há erro no formato, assume conflito
        }
    }
    
    // Getters
    public String getUsuario() { return usuario; }
    public String getInicio() { return inicio; }
    public String getFim() { return fim; }
    public String getMotivo() { return motivo; }
    
    @Override
    public String toString() {
        return String.format("Reserva de %s\nPeríodo: %s às %s\nMotivo: %s", 
            usuario, inicio, fim, motivo);
    }
}