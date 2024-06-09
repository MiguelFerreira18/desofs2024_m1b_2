package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.TipoReceita;

import java.util.List;

public interface TipoReceitaServiceRepo {
    TipoReceita save(TipoReceita receitaService);
    TipoReceita findbyId(Long id);
    List<TipoReceita> findAll();
    TipoReceita findbyName(String name);
    void deleteAll();
    void deleteById(Long id);
}
