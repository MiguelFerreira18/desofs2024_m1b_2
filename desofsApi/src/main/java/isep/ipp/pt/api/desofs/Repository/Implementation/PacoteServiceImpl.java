package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.PacoteRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class PacoteServiceImpl implements PacoteServiceRepo {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PacoteServiceImpl.class);
    @Autowired
    private PacoteRepo pacoteRepo;
    @Autowired
    private LoggerStrategy logger;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;


    @Override
    public Pacote save(Pacote pacoteService) {
        if(!isTesting()) logger.log(pacoteService.copy().toString());
        return pacoteRepo.save(pacoteService);
    }

    @Override
    public Pacote findbyName(String name) {
        return pacoteRepo.findByName(name);
    }

    @Override
    public List<Pacote> findAll() {
        List<Pacote> pacotes = new LinkedList<>();
        for (Pacote pacote : pacoteRepo.findAll()) {
            pacotes.add(pacote);
        }
        return pacotes;
    }

    @Override
    public Pacote findbyId(Long id) {
        if (pacoteRepo.findById(id).isPresent()) {
            return pacoteRepo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void disable(Long id) {
        if (pacoteRepo.findById(id).isPresent()) {
            Pacote pacote = pacoteRepo.findById(id).get();
            pacote.setDisabled();
            if(!isTesting()) logger.log(pacote.copy().toString());
            pacoteRepo.save(pacote);
        }

    }

    @Override
    public void enable(Long id) {
        if (pacoteRepo.findById(id).isPresent()) {
            Pacote pacote = pacoteRepo.findById(id).get();
            pacote.setEnabled();
            if(!isTesting()) logger.log(pacote.copy().toString());
            pacoteRepo.save(pacote);
        }
    }

    @Override
    public void deleteById(Long id) {
        if(!isTesting()) pacoteRepo.findById(id).ifPresent(pacote -> logger.log(pacote.copy().toString()));
        pacoteRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        if(!isTesting()) pacoteRepo.findAll().forEach(pacote -> logger.log(pacote.copy().toString()));
        pacoteRepo.deleteAll();
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }
}
