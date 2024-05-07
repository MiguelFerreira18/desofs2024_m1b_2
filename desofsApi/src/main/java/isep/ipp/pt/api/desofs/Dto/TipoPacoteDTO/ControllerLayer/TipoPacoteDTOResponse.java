package isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer;

import jakarta.validation.constraints.*;

public class TipoPacoteDTOResponse {

    @NotNull
    @Min(value = 0, message = "Id do tipo de pacote inválido")
    private final Long tipoPacoteId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome do pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do tipo de pacote inválido")
    private String nome;

    public TipoPacoteDTOResponse(String nome, Long tipoPacoteId) {
        this.nome = nome;
        this.tipoPacoteId = tipoPacoteId;
    }

    public String getNome() {
        return nome;
    }

    public Long getTipoPacoteId() {
        return tipoPacoteId;
    }
}
