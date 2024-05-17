package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.Receita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepo extends CrudRepository<Receita, Long> {
    @Query("SELECT r FROM Receita r WHERE r.nome = ?1")
    Receita findByName(String name);
}
