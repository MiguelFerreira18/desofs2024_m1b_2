package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Encomenda;

import java.time.LocalDateTime;
import java.util.List;


public interface EncomendaServiceRepo {
    Encomenda save(Encomenda encomendaService);

    Encomenda findbyId(Long id);

    Encomenda findByDateUserPackage(String userId, Long pacoteId, LocalDateTime dataEncomenda);

    List<Encomenda> findAll(String userId);

    void deleteById(Long id);

    void deleteAll();

    List<Encomenda> findEncHistory(String userId);
    void deleteEncomendaByUserName(String userId);
}
