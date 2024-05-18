package isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReceitaDTOServicePatchRequest {
    @Id
    @GeneratedValue
    private Long receitaId;
    @Pattern(regexp = "^(\\.\\/[\\w-]+(\\/[\\w-]+)*\\/[\\w-]+\\.[\\w-]+)$", message = "Invalid path")
    private String path;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Invalid name")
    @NotNull
    private String nome;
    @ManyToOne
    private Long pacote;

    @ManyToOne
    @NotNull
    private Long tipoReceita;

    public ReceitaDTOServicePatchRequest() {
    }

    public ReceitaDTOServicePatchRequest(Long receitaId, String path, String nome, Long pacote, Long tipoReceita) {
        this.receitaId = receitaId;
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
    }

    public ReceitaDTOServicePatchRequest(String path, String nome, Long pacote, Long tipoReceita) {
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

    public void setReceitaId(Long receitaId) {
        this.receitaId = receitaId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPacote(Long pacote) {
        this.pacote = pacote;
    }

    public void setTipoReceita(Long tipoReceita) {
        this.tipoReceita = tipoReceita;
    }
}
