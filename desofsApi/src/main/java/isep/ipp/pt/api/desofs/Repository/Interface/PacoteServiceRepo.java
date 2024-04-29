package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Pacote;

public interface PacoteServiceRepo {
    Pacote save(Pacote pacoteService);
    Pacote findbyId(Long id);
    void disable(Long id);
    void enable(Long id);
}
