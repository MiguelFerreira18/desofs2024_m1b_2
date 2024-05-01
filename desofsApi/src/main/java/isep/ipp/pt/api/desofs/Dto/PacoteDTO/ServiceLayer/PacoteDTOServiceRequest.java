package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;


public class PacoteDTOServiceRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    @Min(value = 0, message = "Nome do pacote inválido")
    @Max(value = 32, message = "Nome do pacote inválido")
    private final String nome;

    @NotBlank
    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    @Min(value = 0, message = "Descrição do pacote inválida")
    @Max(value = 64, message = "Descrição do pacote inválida")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    private final String pacoteDescription;

    private final boolean disabled;
    private final Long tipoPacote;

    public PacoteDTOServiceRequest(String nome, double pacoteBasePrice, String pacoteDescription, boolean disabled, Long tipoPacote) {
        this.nome = nome;
        this.pacoteBasePrice = pacoteBasePrice;
        this.pacoteDescription = pacoteDescription;
        this.disabled = disabled;
        this.tipoPacote = tipoPacote;
    }

    public String getNome() {
        return nome;
    }

    public double getPacoteBasePrice() {
        return pacoteBasePrice;
    }

    public String getPacoteDescription() {
        return pacoteDescription;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public Long getTipoPacote() {
        return tipoPacote;
    }
}
