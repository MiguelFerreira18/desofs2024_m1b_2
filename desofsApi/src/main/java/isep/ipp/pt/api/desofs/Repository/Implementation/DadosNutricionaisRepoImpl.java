package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Repository.Interface.DadosNutricionaisServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.DadosNutricionaisRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DadosNutricionaisRepoImpl implements DadosNutricionaisServiceRepo {

    @Autowired
    private DadosNutricionaisRepo dadosNutricionaisRepo;

    @Override
    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId) {
        return dadosNutricionaisRepo.getDadosNutricionaisByReceitaId(receitaId);
    }

    @Override
    public boolean save(DadosNutricionais dadosNutricionais) {
        dadosNutricionaisRepo.save(dadosNutricionais);
        return true;
    }

    @Override
    public boolean saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        DadosNutricionais dados = dadosNutricionaisRepo.getDadosNutricionaisByReceitaId(receitaId);
        if (dados == null) {
            dadosNutricionaisRepo.save(dadosNutricionais);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        DadosNutricionais dados = dadosNutricionaisRepo.getDadosNutricionaisByReceitaId(receitaId);
        if (dados != null) {
            dadosNutricionaisRepo.deleteById(dados.getDadosNutricionaisId());
            dadosNutricionaisRepo.save(dadosNutricionais);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<DadosNutricionais> getAllDadosNutricionais() {
        return (List<DadosNutricionais>) dadosNutricionaisRepo.findAll();
    }

    @Override
    public void deleteAll() {
        dadosNutricionaisRepo.deleteAll();
    }
}
