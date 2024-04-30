package isep.ipp.pt.api.desofs.Repository.Implementation;


import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.TipoPacoteRepo;
import org.springframework.beans.factory.annotation.Autowired;


public class TipoPacoteServiceImpl implements TipoPacoteServiceRepo {
    @Autowired
    private TipoPacoteRepo tipoPacoteRepo;

    @Override
    public TipoPacote save(TipoPacote pacoteService) {
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
}
