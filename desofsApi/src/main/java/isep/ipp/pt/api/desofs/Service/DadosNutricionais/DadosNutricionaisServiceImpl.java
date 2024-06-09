package isep.ipp.pt.api.desofs.Service.DadosNutricionais;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Repository.Interface.DadosNutricionaisServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosNutricionaisServiceImpl implements DadosNutricionaisService {

    @Autowired
    private DadosNutricionaisServiceRepo dadosNutricionaisServiceRepo;

    @Override
    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId) {
        return dadosNutricionaisServiceRepo.getDadosNutricionaisByReceitaId(receitaId);
    }

    @Override
    public boolean saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        return dadosNutricionaisServiceRepo.saveByReceitaId(receitaId, dadosNutricionais);
    }

    @Override
    public boolean updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        return dadosNutricionaisServiceRepo.updateByReceitaId(receitaId, dadosNutricionais);
    }

    @Override
    public List<DadosNutricionais> getAllDadosNutricionais() {
        return dadosNutricionaisServiceRepo.getAllDadosNutricionais();
    }

    @Override
    public void deleteAll() {
        dadosNutricionaisServiceRepo.deleteAll();
    }
}
