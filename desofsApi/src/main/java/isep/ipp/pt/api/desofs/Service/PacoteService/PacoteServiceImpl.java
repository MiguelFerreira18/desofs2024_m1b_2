package isep.ipp.pt.api.desofs.Service.PacoteService;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacoteServiceImpl implements PacoteService {

    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Override
    public Pacote save(Pacote pacoteService) {
        return pacoteRepo.save(pacoteService);
    }

    @Override
    public Pacote findbyId(Long id) {
        return pacoteRepo.findbyId(id);
    }

    @Override
    public void disable(Long id) {
        pacoteRepo.disable(id);

    }

    @Override
    public void enable(Long id) {
        pacoteRepo.enable(id);
    }
}
