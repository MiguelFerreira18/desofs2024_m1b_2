package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import jakarta.validation.constraints.*;


public class PacoteDTOServiceResponse {

    @NotNull
    @Min(value = 0, message = "Id do pacote inválido")
    private final Long pacoteId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do pacote inválido")
    private final String nome;


    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    @Size(min = 1,max = 64, message = "Descrição do pacote inválida")
    private final String pacoteDescription;

    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    private final boolean disabled;

    @NotNull
    private final TipoPacote tipoPacote;

    public PacoteDTOServiceResponse(String nome, String pacoteDescription, double pacoteBasePrice,Long pacoteId, boolean disabled,TipoPacote tipoPacote) {
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
