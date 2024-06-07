package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.TipoReceitaRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedList;
import java.util.List;

public class TipoReceitaServiceImpl implements TipoReceitaServiceRepo {

    @Autowired
    private TipoReceitaRepo tipoReceitaRepo;
    @Autowired
    private LoggerStrategy logger;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;

    @Override
    public TipoReceita save(TipoReceita receitaService) {
        if(!isTesting()) logger.log(receitaService.copy().toString());
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
        if(!isTesting()) tipoReceitaRepo.findAll().forEach(tipoReceita -> logger.log(tipoReceita.copy().toString()));
        tipoReceitaRepo.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        if(!isTesting()) tipoReceitaRepo.findById(id).ifPresent(tipoReceita -> logger.log(tipoReceita.copy().toString()));
        tipoReceitaRepo.deleteById(id);
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }
}
