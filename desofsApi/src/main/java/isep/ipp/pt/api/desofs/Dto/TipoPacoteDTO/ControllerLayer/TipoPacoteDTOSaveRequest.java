package isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TipoPacoteDTOSaveRequest {


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome do pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do tipo de pacote inválido")
    private String nome;

    public TipoPacoteDTOSaveRequest(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }
}
