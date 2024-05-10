package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.TipoReceitaRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoReceitaRepoImpl implements TipoReceitaServiceRepo {
    @Autowired
    private TipoReceitaRepo tipoReceitaRepo;

    @Override
    public TipoReceita findbyName(String name) {
        return tipoReceitaRepo.findbyName(name);
    }

    @Override
    public void save(TipoReceita tipoReceita) {
        tipoReceitaRepo.save(tipoReceita);
    }
}
