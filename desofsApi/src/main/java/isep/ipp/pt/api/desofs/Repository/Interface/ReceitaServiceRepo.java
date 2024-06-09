package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Receita;

import java.util.List;

public interface ReceitaServiceRepo {
    Receita save(Receita receitaService);
    Receita findbyId(Long id);
    Receita findbyName(String name);
    List<Receita> findAll();
    void deleteById(Long id);
    void deleteAll();

}
