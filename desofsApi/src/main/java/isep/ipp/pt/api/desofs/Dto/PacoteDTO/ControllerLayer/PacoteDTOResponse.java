package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

    private final boolean disabled;

    private final TipoPacote tipoPacote;

    public PacoteDTOResponse(String nome, String pacoteDescription, double pacoteBasePrice,Long pacoteId, boolean disabled,TipoPacote tipoPacote) {
        this.nome = nome;
        this.pacoteDescription = pacoteDescription;
        this.pacoteBasePrice = pacoteBasePrice;
        this.pacoteId = pacoteId;
        this.disabled = disabled;
        this.tipoPacote = tipoPacote;
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

    public boolean getDisabled() {
        return disabled;
    }

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }
}
