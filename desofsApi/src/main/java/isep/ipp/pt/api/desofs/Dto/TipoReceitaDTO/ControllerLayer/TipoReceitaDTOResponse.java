package isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TipoReceitaDTOResponse {
    @Id
    @GeneratedValue
    private Long tipoReceitaId;
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome da receita inválido")
    @NotNull
    @NotBlank
    @Size(min = 1,max = 64, message = "Nome do tipo de receita inválido")
    private String nome;

    public TipoReceitaDTOResponse() {
    }

    public TipoReceitaDTOResponse(Long tipoReceitaId, String nome) {
        this.tipoReceitaId = tipoReceitaId;
        this.nome = nome;
    }

    public TipoReceitaDTOResponse(String nome) {
        this.nome = nome;
    }

    public Long getTipoReceitaId() {
        return tipoReceitaId;
    }

    public String getNome() {
        return nome;
    }

    public void setTipoReceitaId(Long tipoReceitaId) {
        this.tipoReceitaId = tipoReceitaId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
