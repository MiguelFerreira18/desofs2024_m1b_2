package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Receita {

    @Id
    @GeneratedValue
    private Long receitaId;
    @Pattern(regexp = "[^\0]+\\.pdf$", message = "Invalid path")
    private String path;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid name")
    @NotNull
    private String nome;
    @ManyToOne
    private Pacote pacote;

    @ManyToOne
    @NotNull
    private TipoReceita tipoReceita;

    public Receita() {
    }

    public Receita(Long receitaId, String path, String nome, Pacote pacote, TipoReceita tipoReceita) {
        this.receitaId = receitaId;
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public Receita(String path, String nome, Pacote pacote, TipoReceita tipoReceita) {
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public Long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(Long receitaId) {
        this.receitaId = receitaId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public Receita copy(){
        return new Receita(this.receitaId, this.path, this.nome, this.pacote, this.tipoReceita);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Receita{");
        sb.append("receitaId=").append(receitaId);
        sb.append(", path='").append(path).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", pacote=").append(pacote);
        sb.append(", tipoReceita=").append(tipoReceita);
        sb.append('}');
        return sb.toString();
    }
}
