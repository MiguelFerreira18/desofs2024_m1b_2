package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer;

import jakarta.validation.constraints.*;

public class PacoteDTOPatchRequest {

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
    @Positive
    @NotNull
    private final Long tipoPacote;

    public PacoteDTOPatchRequest(Long pacoteId,String nome, double pacoteBasePrice, String pacoteDescription, boolean disabled, Long tipoPacote) {
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

    public Long getTipoPacote() {
        return tipoPacote;
    }

    public Long getPacoteId() {
        return pacoteId;
    }
}
