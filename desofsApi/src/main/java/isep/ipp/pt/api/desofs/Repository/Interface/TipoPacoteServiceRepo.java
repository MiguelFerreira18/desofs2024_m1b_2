package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;

import java.util.List;

public interface TipoPacoteServiceRepo {
    TipoPacote save(TipoPacote pacoteService);
    TipoPacote findbyId(Long id);
    List<TipoPacote> findAll();
    TipoPacote findbyName(String name);
    void deleteAll();
    void deleteById(Long id);

}
