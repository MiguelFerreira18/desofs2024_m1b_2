package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Encomenda;
import isep.ipp.pt.api.desofs.Repository.Interface.EncomendaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.EncomendaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class EncomendaServiceImpl implements EncomendaServiceRepo {

    @Autowired
    private EncomendaRepo encomendaRepo;

    @Override
    public Encomenda save(Encomenda encomendaService) {
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
    public List<Encomenda> findAll() {
        List<Encomenda> encomendas = new LinkedList<>();
        for (Encomenda encomenda : encomendaRepo.findAll()) {
            encomendas.add(encomenda);
        }
        return encomendas;
    }

    @Override
    public void deleteById(Long id) {
        encomendaRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        encomendaRepo.deleteAll();
    }

    @Override
    public List<Encomenda> findEncHistory(Long userId) {
        if (encomendaRepo.findEncHistory(userId) != null) {
            return encomendaRepo.findEncHistory(userId);
        } else {
            return null;
        }
    }
}
