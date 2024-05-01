package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Pacote;

import java.util.List;

public interface PacoteServiceRepo {
    Pacote save(Pacote pacoteService);
    Pacote findbyId(Long id);
    Pacote findbyName(String name);
    List<Pacote> findAll();
    void disable(Long id);
    void enable(Long id);
    void deleteAll();
}
