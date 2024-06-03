package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Long>{

    @Query("SELECT r FROM Review r WHERE r.pacote.pacoteId = ?1")
    public List<Review> getReviewsByPacoteId(Long pacoteId);

    @Query("SELECT r FROM Review r WHERE r.user.userId = ?1")
    public List<Review> getReviewsByUserId(String userId);

    @Query("DELETE FROM Review r WHERE r.user.username = ?1")
    public void deleteReviewsByUserName(String username);

    @Query("SELECT r FROM Review r WHERE r.user.username = ?1")
    public List<Review> getReviewsByUserName(String username);


}
