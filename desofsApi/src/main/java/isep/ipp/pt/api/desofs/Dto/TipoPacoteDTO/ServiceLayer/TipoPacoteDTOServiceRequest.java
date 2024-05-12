package isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TipoPacoteDTOServiceRequest {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do tipo de pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do tipo de pacote inválido")
    private final String nome;


    public TipoPacoteDTOServiceRequest(String nome) {
        this.nome = nome;
    }

    public TipoPacoteDTOServiceRequest() {
        this.nome = "";
    }

    public String getNome() {
        return nome;
    }
}
