package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPacoteRepo extends CrudRepository<TipoPacote, Long>{
}
