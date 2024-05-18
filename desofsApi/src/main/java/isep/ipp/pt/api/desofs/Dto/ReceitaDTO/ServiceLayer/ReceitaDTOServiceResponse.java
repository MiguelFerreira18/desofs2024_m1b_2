package isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReceitaDTOServiceResponse {
    @Id
    @GeneratedValue
    private Long receitaId;
    @Pattern(regexp = "^(\\.\\/[\\w-]+(\\/[\\w-]+)*\\/[\\w-]+\\.[\\w-]+)$", message = "Invalid path")
    private String path;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid name")
    @NotNull
    private String nome;
    @ManyToOne
    private Pacote pacote;

    @ManyToOne
    @NotNull
    private TipoReceita tipoReceita;

    public ReceitaDTOServiceResponse() {
    }

    public ReceitaDTOServiceResponse(Long receitaId, String path, String nome, Pacote pacote, TipoReceita tipoReceita) {
        this.receitaId = receitaId;
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public ReceitaDTOServiceResponse(String path, String nome, Pacote pacote, TipoReceita tipoReceita) {
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
}
