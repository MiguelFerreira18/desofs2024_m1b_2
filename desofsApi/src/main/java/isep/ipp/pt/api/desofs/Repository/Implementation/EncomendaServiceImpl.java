package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Encomenda;
import isep.ipp.pt.api.desofs.Repository.Interface.EncomendaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.EncomendaRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EncomendaServiceImpl implements EncomendaServiceRepo {

    @Autowired
    private EncomendaRepo encomendaRepo;
    @Autowired
    private LoggerStrategy databaseLogger;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;

    @Override
    public Encomenda save(Encomenda encomendaService) {
        if(!isTesting()) databaseLogger.log(encomendaService.copy(encoder).toString());
        return encomendaRepo.save(encomendaService);
    }

    @Override
    public Encomenda findbyId(Long id) {
        if (encomendaRepo.findById(id).isPresent()) {
            return encomendaRepo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Encomenda findByDateUserPackage(String userId, Long pacoteId, LocalDateTime dataEncomenda) {
        return encomendaRepo.findByDateUserPackage(userId,pacoteId,dataEncomenda);
    }

    @Override
    public List<Encomenda> findAll(String userId) {
        List<Encomenda> encomendas = new LinkedList<>();
        for (Encomenda encomenda : encomendaRepo.findAll()) {
            if(Objects.equals(encomenda.getUser().getUserId(), userId)) {
                encomendas.add(encomenda);
            }
        }
        return encomendas;
    }

    @Override
    public void deleteById(Long id) {
        if(!isTesting()) encomendaRepo.findById(id).ifPresent(encomenda -> databaseLogger.log(encomenda.copy(encoder).toString()));
        encomendaRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        if(!isTesting()) encomendaRepo.findAll().forEach(encomenda -> databaseLogger.log(encomenda.copy(encoder).toString()));
        encomendaRepo.deleteAll();
    }

    @Override
    public List<Encomenda> findEncHistory(String userId) {
        if (encomendaRepo.findEncHistory(userId) != null) {
            return encomendaRepo.findEncHistory(userId);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEncomendaByUserName(String userId) {
        encomendaRepo.deleteEncomendaByUserName(userId);
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }
}
