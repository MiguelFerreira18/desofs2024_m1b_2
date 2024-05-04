package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Pacote {

    @Id
    @GeneratedValue
    private Long pacoteId;


    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    private String nome;

    @Min(value = 0, message = "Peço do pacote inválido")
    @Max(value = 400, message = "Peço do pacote inválido")
    private double pacoteBasePrice;

    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    private String pacoteDescription;

    @NotNull
    private boolean disabled;

    @ManyToOne
    private TipoPacote tipoPacote;

    public Pacote() {
    }

    public Pacote(Long pacoteId, String nome, double pacoteBasePrice, String pacoteDescription, boolean disabled, TipoPacote tipoPacote) {
        this.pacoteId = pacoteId;
        this.nome = nome;
        this.pacoteBasePrice = pacoteBasePrice;
        this.pacoteDescription = pacoteDescription;
        this.disabled = disabled;
        this.tipoPacote = tipoPacote;
    }

    public Pacote(String nome, double pacoteBasePrice, String pacoteDescription,boolean disabled, TipoPacote tipoPacote) {
        this.nome = nome;
        this.pacoteBasePrice = pacoteBasePrice;
        this.pacoteDescription = pacoteDescription;
        this.disabled = disabled;
        this.tipoPacote = tipoPacote;
    }

    public Long getPacoteId() {
        return pacoteId;
    }

    public void setPacoteId(Long pacoteId) {
        this.pacoteId = pacoteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPacoteBasePrice() {
        return pacoteBasePrice;
    }

    public void setPacoteBasePrice(double pacoteBasePrice) {
        this.pacoteBasePrice = pacoteBasePrice;
    }

    public String getPacoteDescription() {
        return pacoteDescription;
    }

    public void setPacoteDescription(String pacoteDescription) {
        this.pacoteDescription = pacoteDescription;
    }

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }

    public void setTipoPacote(TipoPacote tipoPacote) {
        this.tipoPacote = tipoPacote;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Pacote setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public Pacote setDisabled() {
        this.disabled = true;
        return this;
    }
    public Pacote setEnabled() {
        this.disabled = false;
        return this;
    }
}
