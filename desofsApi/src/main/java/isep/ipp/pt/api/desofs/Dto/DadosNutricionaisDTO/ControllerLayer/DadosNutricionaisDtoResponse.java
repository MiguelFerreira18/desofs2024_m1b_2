package isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer;

public record DadosNutricionaisDtoResponse (
        Long receitaId,
        String porcao,
        String valorEnergetico,
        String carboidratos,
        String proteinas,
        String gordura,
        String sal,
        String acucar
) {
    public DadosNutricionaisDtoResponse {
        if (receitaId == null || porcao == null || valorEnergetico == null || carboidratos == null || proteinas == null || gordura == null || sal == null || acucar == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
    }
}