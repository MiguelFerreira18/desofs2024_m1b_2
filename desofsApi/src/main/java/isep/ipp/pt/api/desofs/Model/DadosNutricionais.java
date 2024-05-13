package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class DadosNutricionais {

    @Id
    @GeneratedValue
    private Long dadosNutricionaisId;

    @NotNull
    private Long receitaId;

    @NotNull
    private String porcao;

    @NotNull
    private String valorEnergetico;

    @NotNull
    private String carboidratos;

    @NotNull
    private String proteinas;

    @NotNull
    private String gordura;

    @NotNull
    private String sal;

    @NotNull
    private String acucar;

    public DadosNutricionais() {
    }

    // WITH ALL
    public DadosNutricionais(Long dadosNutricionaisId, Long receitaId, String porcao, String valorEnergetico, String carboidratos, String proteinas, String gordura, String sal, String acucar) {
        this.dadosNutricionaisId = dadosNutricionaisId;
        this.receitaId = receitaId;
        this.porcao = porcao;
        this.valorEnergetico = valorEnergetico;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.gordura = gordura;
        this.sal = sal;
        this.acucar = acucar;
    }

    public DadosNutricionais(Long receitaId, String porcao, String valorEnergetico, String carboidratos, String proteinas, String gordura, String sal, String acucar) {
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
