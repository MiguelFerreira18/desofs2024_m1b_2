package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class TipoReceita {
    @Id
    @GeneratedValue
    private Long tipoReceitaId;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome da receita inválido")
    @NotNull
    @NotBlank
    @Size(min = 1,max = 64, message = "Nome do tipo de receita inválido")
    private String nome;

    public TipoReceita() {
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

    public TipoReceita copy() {
        return new TipoReceita(this.tipoReceitaId, this.nome);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TipoReceita{");
        sb.append("tipoReceitaId=").append(tipoReceitaId);
        sb.append(", nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
