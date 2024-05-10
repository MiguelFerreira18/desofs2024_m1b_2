package isep.ipp.pt.api.desofs.Service.DadosNutricionais;


import isep.ipp.pt.api.desofs.Model.DadosNutricionais;

public interface DadosNutricionaisService {

    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId);

    public void saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);

    public void updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais);
}
