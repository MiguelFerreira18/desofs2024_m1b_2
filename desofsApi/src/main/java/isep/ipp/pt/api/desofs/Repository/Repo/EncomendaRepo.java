package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.Encomenda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EncomendaRepo extends CrudRepository<Encomenda, Long> {
    @Query("SELECT e FROM Encomenda e WHERE e.user.userId = ?1")
    List<Encomenda> findEncHistory(String userId);

    @Query("SELECT e FROM Encomenda e WHERE e.user.userId = ?1 AND e.pacote.pacoteId = ?2 AND e.dataEncomenda = ?3")
    Encomenda findByDateUserPackage(String userId, Long pacoteId, LocalDateTime dataEncomenda);

    @Query("DELETE FROM Encomenda e WHERE e.user.username = ?1")
    void deleteEncomendaByUserName(String username);
}

