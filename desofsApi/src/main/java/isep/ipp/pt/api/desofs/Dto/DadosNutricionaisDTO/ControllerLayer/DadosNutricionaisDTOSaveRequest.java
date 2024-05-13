package isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DadosNutricionaisDTOSaveRequest {
    @NotNull
    private Long receitaId;
    @NotBlank
    @Size(min = 1, message = "Porção inválida")
    @Size(max = 64, message = "Porção inválida")
    private String porcao;
    @NotBlank
    @Size(min = 1, message = "Valor energetico inválido")
    @Size(max = 64, message = "Valor energetico inválido")
    private String valorEnergetico;
    @NotBlank
    @Size(min = 1, message = "Carboidratos inválido")
    @Size(max = 64, message = "Carboidratos inválido")
    private String carboidratos;
    @NotBlank
    @Size(min = 1, message = "Proteinas inválido")
    @Size(max = 64, message = "Proteinas inválido")
    private String proteinas;
    @NotBlank
    @Size(min = 1, message = "Gordura inválido")
    @Size(max = 64, message = "Gordura inválido")
    private String gordura;
    @NotBlank
    @Size(min = 1, message = "Sal inválido")
    @Size(max = 64, message = "Sal inválido")
    private String sal;
    @NotBlank
    @Size(min = 1, message = "Acucar inválido")
    @Size(max = 64, message = "Acucar inválido")
    private String acucar;

    public DadosNutricionaisDTOSaveRequest(Long receitaId, String porcao, String valorEnergetico, String carboidratos, String proteinas, String gordura, String sal, String acucar) {
        this.receitaId = receitaId;
        this.porcao = porcao;
        this.valorEnergetico = valorEnergetico;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.gordura = gordura;
        this.sal = sal;
        this.acucar = acucar;
    }
}
