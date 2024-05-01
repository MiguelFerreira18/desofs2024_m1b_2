package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class PacoteDTOResponse {

    @NotNull
    @Min(value = 0, message = "Id do pacote inválido")
    private final Long pacoteId;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    private final String nome;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    private final String pacoteDescription;
    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    public PacoteDTOResponse(String nome, String pacoteDescription, double pacoteBasePrice,Long pacoteId) {
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
