package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Encomenda;

import java.util.List;


public interface EncomendaServiceRepo {
    Encomenda save(Encomenda encomendaService);

    Encomenda findbyId(Long id);

    List<Encomenda> findAll();

    void deleteById(Long id);

    void deleteAll();

    List<Encomenda> findEncHistory(Long userId);
}
