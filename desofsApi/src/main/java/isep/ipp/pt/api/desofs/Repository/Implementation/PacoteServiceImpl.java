package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.PacoteRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class PacoteServiceImpl implements PacoteServiceRepo {

    @Autowired
    private PacoteRepo pacoteRepo;


    @Override
    public Pacote save(Pacote pacoteService) {
        return pacoteRepo.save(pacoteService);
    }

    @Override
    public Pacote findbyId(Long id) {
        if (pacoteRepo.findById(id).isPresent()){
            return pacoteRepo.findById(id).get();
        }else {
            return null;
        }
    }

    @Override
    public void disable(Long id) {
        if (pacoteRepo.findById(id).isPresent()) {
            Pacote pacote = pacoteRepo.findById(id).get();
            pacote.setDisabled();
            pacoteRepo.save(pacote);
        }

    }

    @Override
    public void enable(Long id) {
        if (pacoteRepo.findById(id).isPresent()) {
            Pacote pacote = pacoteRepo.findById(id).get();
            pacote.setEnabled();
            pacoteRepo.save(pacote);
        }
    }
    @Override
    public void deleteAll() {
        pacoteRepo.deleteAll();
    }
}
