package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserServiceRepo {
    public User getUserById(Long userId);
    public List<User> getAllUsers();
    public UserDetails findByUsername(final String username);

    public User saveUser(User user);

}
