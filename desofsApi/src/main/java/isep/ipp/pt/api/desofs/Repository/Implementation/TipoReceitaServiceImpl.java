package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.TipoReceitaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class TipoReceitaServiceImpl implements TipoReceitaServiceRepo {

    @Autowired
    private TipoReceitaRepo tipoReceitaRepo;

    @Override
    public TipoReceita save(TipoReceita receitaService) {
        return tipoReceitaRepo.save(receitaService);
    }

    @Override
    public TipoReceita findbyId(Long id) {
        if (tipoReceitaRepo.findById(id).isPresent()) {
            return tipoReceitaRepo.findById(id).get();
        }else {
            return null;
        }
    }

    @Override
    public List<TipoReceita> findAll() {
        List<TipoReceita> lista = new LinkedList<>();
        for (TipoReceita tipoReceita : tipoReceitaRepo.findAll()) {
            lista.add(tipoReceita);
        }
        return lista;
    }

    @Override
    public TipoReceita findbyName(String name) {
        return tipoReceitaRepo.findByNome(name);
    }

    @Override
    public void deleteAll() {
        tipoReceitaRepo.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        tipoReceitaRepo.deleteById(id);
    }
}
