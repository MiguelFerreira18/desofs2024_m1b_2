package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import jakarta.validation.constraints.*;

public class PacotePatchDTOService {

    @NotNull
    @Positive
    private final Long pacoteId;


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    @Size(min = 1, message = "Nome do pacote inválido")
    @Size(max = 16, message = "Nome do pacote inválido")
    private final String nome;


    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    @Size(min = 1, message = "Descrição do pacote inválida")
    @Size(max = 64, message = "Descrição do pacote inválida")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    private final String pacoteDescription;

    private final boolean disabled;
    @NotNull
    private final TipoPacote tipoPacote;

    public PacotePatchDTOService(Long pacoteId,String nome, double pacoteBasePrice, String pacoteDescription, boolean disabled, TipoPacote tipoPacote) {
        this.nome = nome;
        this.pacoteBasePrice = pacoteBasePrice;
        this.pacoteDescription = pacoteDescription;
        this.disabled = disabled;
        this.tipoPacote = tipoPacote;
        this.pacoteId = pacoteId;
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

    public boolean getDisabled() {
        return disabled;
    }

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }

    public Long getPacoteId() {
        return pacoteId;
    }
}
