package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.Encomenda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncomendaRepo extends CrudRepository<Encomenda, Long> {
    @Query("SELECT e FROM Encomenda e WHERE e.user.userId = ?1")
    List<Encomenda> findEncHistory(Long userId);
}

