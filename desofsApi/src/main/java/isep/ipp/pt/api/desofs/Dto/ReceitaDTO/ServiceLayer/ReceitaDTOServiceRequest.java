package isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReceitaDTOServiceRequest {
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

    public ReceitaDTOServiceRequest() {
    }

    public ReceitaDTOServiceRequest(String path, String nome, Long pacote, Long tipoReceita) {
        this.path = path;
        this.nome = nome;
        this.pacote = pacote;
        this.tipoReceita = tipoReceita;
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
