package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;

public interface TipoPacoteServiceRepo {
    TipoPacote save(TipoPacote pacoteService);
    TipoPacote findbyId(Long id);

}
