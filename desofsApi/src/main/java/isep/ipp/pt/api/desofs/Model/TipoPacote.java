package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class TipoPacote {
    @Id
    private Long tipoPacoteId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome do pacote inválido")
    private String nome;

    protected TipoPacote() {
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
}
