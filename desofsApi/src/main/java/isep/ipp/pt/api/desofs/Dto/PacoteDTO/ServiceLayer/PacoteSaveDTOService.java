package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import jakarta.validation.constraints.*;

public class PacoteSaveDTOService {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do pacote inválido")
    private final String nome;

    @NotBlank
    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    @Size(min = 1,max = 64, message = "Descrição do pacote inválida")
    private final String pacoteDescription;

    private final boolean disabled;
    private final TipoPacote tipoPacote;

    public PacoteSaveDTOService(String nome, double pacoteBasePrice, String pacoteDescription, boolean disabled, TipoPacote tipoPacote) {
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

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }
}
