package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Encomenda;
import isep.ipp.pt.api.desofs.Repository.Interface.EncomendaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.EncomendaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    public Encomenda findByDateUserPackage(Long userId, Long pacoteId, LocalDateTime dataEncomenda) {
        return encomendaRepo.findByDateUserPackage(userId,pacoteId,dataEncomenda);
    }

    @Override
    public List<Encomenda> findAll(Long userId) {
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
