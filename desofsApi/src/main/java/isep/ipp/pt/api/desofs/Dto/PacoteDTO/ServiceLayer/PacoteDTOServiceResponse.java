package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


public class PacoteDTOServiceResponse {


    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    private final String nome;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    private final String pacoteDescription;
    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    public PacoteDTOServiceResponse(String nome, String pacoteDescription, double pacoteBasePrice) {
        this.nome = nome;
        this.pacoteDescription = pacoteDescription;
        this.pacoteBasePrice = pacoteBasePrice;
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
}
