package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Receita;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.ReceitaRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;

public class ReceitaServiceImpl implements ReceitaServiceRepo {

    @Autowired
    private ReceitaRepo receitaRepo;
    @Autowired
    private LoggerStrategy databaseLogger;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;

    @Override
    public Receita save(Receita receitaService) {
        if(!isTesting()) databaseLogger.log(receitaService.copy().toString());
        return receitaRepo.save(receitaService);
    }

    @Override
    public Receita findbyId(Long id) {
        if (receitaRepo.findById(id).isPresent()) {
            return receitaRepo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Receita findbyName(String name) {
        return receitaRepo.findByName(name);
    }

    @Override
    public List<Receita> findAll() {
        List<Receita> receitas = new LinkedList<>();
        for (Receita receita : receitaRepo.findAll()) {
            receitas.add(receita);
        }
        return receitas;
    }

    @Override
    public void deleteById(Long id) {
        if(!isTesting()) receitaRepo.findById(id).ifPresent(receita -> databaseLogger.log(receita.copy().toString()));
        receitaRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        receitaRepo.deleteAll();
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }
}
