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
    List<Encomenda> findEncHistory(Long userId);

    @Query("SELECT e FROM Encomenda e WHERE e.user.userId = ?1 AND e.pacote.pacoteId = ?2 AND e.dataEncomenda = ?3")
    Encomenda findByDateUserPackage(Long userId, Long pacoteId, LocalDateTime dataEncomenda);
}

