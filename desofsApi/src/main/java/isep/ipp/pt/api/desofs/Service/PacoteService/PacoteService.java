package isep.ipp.pt.api.desofs.Service.PacoteService;

import isep.ipp.pt.api.desofs.Model.Pacote;

public interface PacoteService {
    Pacote save(Pacote pacoteService);
    Pacote findbyId(Long id);
    void disable(Long id);
    void enable(Long id);
}
