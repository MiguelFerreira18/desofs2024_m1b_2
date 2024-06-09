package isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Receita;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReceitaDTOPatchRequest {
    @Id
    @GeneratedValue
    private Long receitaId;
    @Pattern(regexp = "[^\0]+\\.pdf$", message = "Invalid path")
    private String path;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid name")
    @NotNull
    private String nome;
    @ManyToOne
    private Long pacote;

    @ManyToOne
    @NotNull
    private Long tipoReceita;

    public ReceitaDTOPatchRequest() {
    }

    public ReceitaDTOPatchRequest(Long receitaId, String path, String nome, Long pacote, Long tipoReceita) {
        this.receitaId = receitaId;
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public ReceitaDTOPatchRequest(String path, String nome, Long pacote, Long tipoReceita) {
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


    public Long getPacote() {
        return pacote;
    }

    public Long getTipoReceita() {
        return tipoReceita;
    }
}
