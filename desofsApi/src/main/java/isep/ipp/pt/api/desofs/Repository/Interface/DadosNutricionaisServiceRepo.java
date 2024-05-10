package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;

public interface DadosNutricionaisServiceRepo {

    DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId);

    void save(DadosNutricionais dadosNutricionais);

    void saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);

    void updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);
}
