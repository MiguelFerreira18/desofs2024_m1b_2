package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosNutricionaisRepo extends CrudRepository<DadosNutricionais, Long> {

    @Query("SELECT d FROM DadosNutricionais d WHERE d.receitaId = ?1")
    public DadosNutricionais getDadosNutricionaisByReceitaId(Long receitaId);

}
