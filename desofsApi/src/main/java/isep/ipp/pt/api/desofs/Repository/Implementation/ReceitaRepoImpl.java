package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Receita;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.ReceitaRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceitaRepoImpl implements ReceitaServiceRepo {

    @Autowired
    private ReceitaRepo receitaRepo;

    @Override
    public Receita findbyId(Long id) {
        return receitaRepo.findById(id).orElse(null);
    }

    @Override
    public void save(Receita receita) {
        receitaRepo.save(receita);
    }
}
