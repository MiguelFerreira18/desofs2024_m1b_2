package isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public class TipoReceitaDTOServiceRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome da receita inválido")
    @NotNull
    @NotBlank
    @Size(min = 1,max = 64, message = "Nome do tipo de receita inválido")
    private String nome;

    public TipoReceitaDTOServiceRequest() {
    }

    public TipoReceitaDTOServiceRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
