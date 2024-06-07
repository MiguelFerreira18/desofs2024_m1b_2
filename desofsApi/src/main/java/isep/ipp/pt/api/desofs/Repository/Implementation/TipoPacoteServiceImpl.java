package isep.ipp.pt.api.desofs.Repository.Implementation;


import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.TipoPacoteRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedList;
import java.util.List;


public class TipoPacoteServiceImpl implements TipoPacoteServiceRepo {

    @Autowired
    private TipoPacoteRepo tipoPacoteRepo;
    @Autowired
    private LoggerStrategy logger;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;


    @Override
    public TipoPacote save(TipoPacote pacoteService) {
        if(!isTesting()) logger.log(pacoteService.copy().toString());
        return tipoPacoteRepo.save(pacoteService);
    }

    @Override
    public TipoPacote findbyId(Long id) {
        if (tipoPacoteRepo.findById(id).isPresent()) {
            return tipoPacoteRepo.findById(id).get();
        }else {
            return null;
        }
    }

    @Override
    public List<TipoPacote> findAll() {
        List<TipoPacote> lista = new LinkedList<>();
        for (TipoPacote tipoPacote : tipoPacoteRepo.findAll()) {
            lista.add(tipoPacote);
        }
        return lista;
    }

    @Override
    public TipoPacote findbyName(String name) {
        return tipoPacoteRepo.findByNome(name);
    }

    @Override
    public void deleteAll() {
        if(!isTesting()) tipoPacoteRepo.findAll().forEach(tipoPacote -> logger.log(tipoPacote.copy().toString()));
        tipoPacoteRepo.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        if(!isTesting()) tipoPacoteRepo.findById(id).ifPresent(tipoPacote -> logger.log(tipoPacote.copy().toString()));
        tipoPacoteRepo.deleteById(id);
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }
}
