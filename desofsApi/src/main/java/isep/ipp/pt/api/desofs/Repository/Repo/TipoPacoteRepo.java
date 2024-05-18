package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPacoteRepo extends CrudRepository<TipoPacote, Long>{

    @Query("SELECT t FROM TipoPacote t WHERE t.nome = ?1")
    TipoPacote findByNome(String nome);
}
