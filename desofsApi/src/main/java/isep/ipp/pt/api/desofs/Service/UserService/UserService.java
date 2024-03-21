package isep.ipp.pt.api.desofs.Service.UserService;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    public User getUserById(Long userId);
    public List<User> getAllUsers();
    public UserDetails findByUsername(final String username);

    public boolean saveUser(User user);

}
