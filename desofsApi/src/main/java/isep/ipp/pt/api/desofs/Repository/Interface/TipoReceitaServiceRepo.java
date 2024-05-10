package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.TipoReceita;

public interface TipoReceitaServiceRepo {

    TipoReceita findbyName(String name);

    void save(TipoReceita tipoReceita);
}
