package isep.ipp.pt.api.desofs.Service.DadosNutricionais;


import isep.ipp.pt.api.desofs.Model.DadosNutricionais;

import java.util.List;

public interface DadosNutricionaisService {

    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId);

    public boolean saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);

    public boolean updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);

    public List<DadosNutricionais> getAllDadosNutricionais();

    public void deleteAll();
}
