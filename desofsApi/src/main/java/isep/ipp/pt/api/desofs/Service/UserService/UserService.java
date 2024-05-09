package isep.ipp.pt.api.desofs.Service.UserService;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOSignup;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    public User getUserById(Long userId);
    public List<User> getAllUsers();
    public UserDetails findByUsername(final String username);

    UserDetails loadUserByUsername(String username);

    public User validateUser(User user);

    public UserView registerUser(UserDTOSignup user);

    public void deleteAll();

    public void saveUser(User user);
}
