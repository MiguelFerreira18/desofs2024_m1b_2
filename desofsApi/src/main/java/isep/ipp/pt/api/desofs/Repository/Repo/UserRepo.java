package isep.ipp.pt.api.desofs.Repository.Repo;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {


    public User findByUsername(String username);

}
