package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;

import java.util.List;

public interface DadosNutricionaisServiceRepo {

    DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId);

    boolean save(DadosNutricionais dadosNutricionais);

    boolean saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);

    boolean updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);

    List<DadosNutricionais> getAllDadosNutricionais();

    void deleteAll();
}
