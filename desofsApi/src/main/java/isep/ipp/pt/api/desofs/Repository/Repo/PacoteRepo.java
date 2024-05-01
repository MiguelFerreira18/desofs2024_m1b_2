package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.Pacote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteRepo extends CrudRepository<Pacote, Long> {

    @Query("SELECT p FROM Pacote p WHERE p.nome = ?1")
    Pacote findByName(String name);

}
