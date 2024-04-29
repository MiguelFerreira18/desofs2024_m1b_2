package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

@Entity
public class TipoReceita {
    @Id
    private Long tipoReceitaId;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome da receita inválido")

    private String nome;

    protected TipoReceita() {
    }

    public TipoReceita(Long tipoReceitaId, String nome) {
        this.tipoReceitaId = tipoReceitaId;
        this.nome = nome;
    }

    public TipoReceita(String nome) {
        this.nome = nome;
    }

    public Long getTipoReceitaId() {
        return tipoReceitaId;
    }

    public void setTipoReceitaId(Long tipoReceitaId) {
        this.tipoReceitaId = tipoReceitaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
