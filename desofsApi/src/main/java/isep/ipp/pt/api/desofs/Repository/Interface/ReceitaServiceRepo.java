package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Receita;

public interface ReceitaServiceRepo {

    Receita findbyId(Long id);

    void save(Receita receita);

    void deleteAll();
}
