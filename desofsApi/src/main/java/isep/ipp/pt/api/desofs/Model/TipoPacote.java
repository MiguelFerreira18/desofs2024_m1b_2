package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class TipoPacote {

    @Id
    @GeneratedValue
    private Long tipoPacoteId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome do pacote inválido")
    private String nome;

    public TipoPacote() {
    }

    public TipoPacote(Long tipoPacoteId, String nome) {
        this.tipoPacoteId = tipoPacoteId;
        this.nome = nome;
    }

    public TipoPacote(String nome) {
        this.nome = nome;
    }

    public Long getTipoPacoteId() {
        return tipoPacoteId;
    }

    public void setTipoPacoteId(Long tipoPacoteId) {
        this.tipoPacoteId = tipoPacoteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPacote copy() {
        return new TipoPacote(this.tipoPacoteId, this.nome);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TipoPacote{");
        sb.append("tipoPacoteId=").append(tipoPacoteId);
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
