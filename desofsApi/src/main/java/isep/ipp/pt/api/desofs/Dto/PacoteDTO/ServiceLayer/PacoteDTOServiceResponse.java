package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import jakarta.validation.constraints.*;
import lombok.Getter;


public class PacoteDTOServiceResponse {

    @NotNull
    @Min(value = 0, message = "Id do pacote inválido")
    private final Long pacoteId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do pacote inválido")
    private final String nome;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    @Size(min = 1,max = 64, message = "Descrição do pacote inválida")
    private final String pacoteDescription;

    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    public PacoteDTOServiceResponse(String nome, String pacoteDescription, double pacoteBasePrice,Long pacoteId) {
        this.nome = nome;
        this.pacoteDescription = pacoteDescription;
        this.pacoteBasePrice = pacoteBasePrice;
        this.pacoteId = pacoteId;
    }

    public String getNome() {
        return nome;
    }

    public String getPacoteDescription() {
        return pacoteDescription;
    }

    public double getPacoteBasePrice() {
        return pacoteBasePrice;
    }

    public Long getPacoteId() {
        return pacoteId;
    }
}
