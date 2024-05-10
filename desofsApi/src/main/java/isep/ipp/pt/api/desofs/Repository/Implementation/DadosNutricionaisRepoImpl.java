package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Repository.Interface.DadosNutricionaisServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.DadosNutricionaisRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class DadosNutricionaisRepoImpl implements DadosNutricionaisServiceRepo {

    @Autowired
    private DadosNutricionaisRepo dadosNutricionaisRepo;

    @Override
    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId) {
        return dadosNutricionaisRepo.getDadosNutricionaisByReceitaId(receitaId);
    }

    @Override
    public void save(DadosNutricionais dadosNutricionais) {
        dadosNutricionaisRepo.save(dadosNutricionais);
    }

    @Override
    public void saveByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        DadosNutricionais dados = dadosNutricionaisRepo.getDadosNutricionaisByReceitaId(receitaId);
        if (dados == null) {
            dadosNutricionaisRepo.save(dadosNutricionais);
        } else {
            throw new IllegalArgumentException("Receita já tem dados nutricionais associados!");
        }
    }

    @Override
    public void updateByReceitaId(Long receitaId, DadosNutricionais dadosNutricionais) {
        DadosNutricionais dados = dadosNutricionaisRepo.getDadosNutricionaisByReceitaId(receitaId);
        if (dados != null) {
            dadosNutricionaisRepo.deleteById(dados.getDadosNutricionaisId());
            dadosNutricionaisRepo.save(dadosNutricionais);
        } else {
            throw new IllegalArgumentException("Receita não tem dados nutricionais associados!");
        }
    }
}
