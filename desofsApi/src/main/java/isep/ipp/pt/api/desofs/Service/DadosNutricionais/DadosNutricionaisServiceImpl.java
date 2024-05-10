package isep.ipp.pt.api.desofs.Service.DadosNutricionais;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Repository.Interface.DadosNutricionaisServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DadosNutricionaisServiceImpl implements DadosNutricionaisService {

    @Autowired
    private DadosNutricionaisServiceRepo dadosNutricionaisServiceRepo;

    @Override
    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId) {
        return dadosNutricionaisServiceRepo.getDadosNutricionaisByReceitaId(receitaId);
    }

    @Override
    public void saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        dadosNutricionaisServiceRepo.saveByReceitaId(receitaId, dadosNutricionais);
    }

    @Override
    public void updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        dadosNutricionaisServiceRepo.updateByReceitaId(receitaId, dadosNutricionais);
    }
}
