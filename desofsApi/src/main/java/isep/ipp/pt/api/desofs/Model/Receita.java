package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;

@Entity
public class Receita {

    @Id
    private Long receitaId;
    @Pattern(regexp = "^(\\.\\/[\\w-]+(\\/[\\w-]+)*\\/[\\w-]+\\.[\\w-]+)$", message = "Invalid path")
    private String path;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid name")
    private String nome;
    @ManyToOne
    private Pacote pacote;

    @ManyToOne
    private TipoReceita tipoReceita;

    protected Receita() {
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

    public Receita setReceitaId(Long receitaId) {
        this.receitaId = receitaId;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Receita setPath(String path) {
        this.path = path;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Receita setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public Receita setPacote(Pacote pacote) {
        this.pacote = pacote;
        return this;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public Receita setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
        return this;
    }
}
