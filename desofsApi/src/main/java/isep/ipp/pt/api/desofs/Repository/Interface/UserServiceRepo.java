package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer.UserDTOPasswordChange;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserServiceRepo {
    public User getUserById(String userId);
    public List<User> getAllUsers();
    public User findByUserByEmail(String email);
    public UserDetails findByUsername(final String username);
    public void deleteAll();
    public User saveUser(User user);
    public User validateUser(String username, String password);
    public void deleteUser(String username);

    public boolean changePassword(Long user, UserDTOPasswordChange password);

}
