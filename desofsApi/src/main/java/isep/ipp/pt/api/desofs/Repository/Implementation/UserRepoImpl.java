package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer.UserDTOPasswordChange;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;



public class UserRepoImpl implements UserServiceRepo {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User getUserById(Long userId) {
        return userRepo.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User findByUserByEmail(String email) {
        return userRepo.findByUsername(email);
    }

    @Override
    public UserDetails findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }
  
    @Override
    public User validateUser(String username, String password) {
        return userRepo.validateUser(username, password);
    }

    @Override
    public boolean changePassword(Long user, UserDTOPasswordChange password) {
        String newPassword = password.getNewPassword();
        newPassword = encoder.encode(newPassword);
        User u = userRepo.getUserById(user);
        u.setPassword(newPassword);
        userRepo.deleteById(user);
        userRepo.save(u);
        return true;
    }
}
