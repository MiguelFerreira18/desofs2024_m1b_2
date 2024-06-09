package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.TipoReceita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoReceitaRepo extends CrudRepository<TipoReceita, Long> {
    @Query("SELECT t FROM TipoReceita t WHERE t.nome = ?1")
    TipoReceita findByNome(String nome);
}
