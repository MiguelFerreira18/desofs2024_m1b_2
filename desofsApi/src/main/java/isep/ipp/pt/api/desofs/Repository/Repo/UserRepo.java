package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {


    public User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    public User validateUser(String username, String password);
}
