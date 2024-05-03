package isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer;

import jakarta.validation.constraints.*;


public class PacoteDTOServiceRequest {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Nome do pacote inválido")
    @Size(min = 1,max = 16, message = "Nome do pacote inválido")
    private final String nome;

    @Min(value = 0, message = "Preço base do pacote inválido")
    @Max(value = 500, message = "Preço base do pacote inválido")
    private final double pacoteBasePrice;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Descrição do pacote inválida")
    @Size(min = 1,max = 64, message = "Descrição do pacote inválida")
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

    public boolean getDisabled() {
        return disabled;
    }

    public Long getTipoPacote() {
        return tipoPacote;
    }
}
