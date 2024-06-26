package isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReceitaDTOResponse {
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

    public ReceitaDTOResponse() {
    }

    public ReceitaDTOResponse(Long receitaId, String path, String nome, Pacote pacote, TipoReceita tipoReceita) {
        this.receitaId = receitaId;
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public ReceitaDTOResponse(String path, String nome, Pacote pacote, TipoReceita tipoReceita) {
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public Long getReceitaId() {
        return receitaId;
    }

    public String getPath() {
        return path;
    }

    public String getNome() {
        return nome;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public TipoReceita getTipoReceita() {
        return tipoReceita;
    }

    public void setReceitaId(Long receitaId) {
        this.receitaId = receitaId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public void setTipoReceita(TipoReceita tipoReceita) {
        this.tipoReceita = tipoReceita;
    }
}
