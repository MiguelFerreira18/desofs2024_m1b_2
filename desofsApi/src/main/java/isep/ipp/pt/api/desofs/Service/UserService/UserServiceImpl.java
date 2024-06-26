package isep.ipp.pt.api.desofs.Service.UserService;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOSignup;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer.UserDTOPasswordChange;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserServiceRepo userRepo;

    @Override
    public User getUserById(String userId) {
        User user = userRepo.getUserById(userId);
        if (user == null) throw new IllegalArgumentException("User not found");
        return userRepo.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public UserDetails findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User validateUser(User user) {
        if(user.getUsername() == null || user.getPassword() == null) {
            return null;
        }
        return userRepo.validateUser(user.getUsername(), encoder.encode(user.getPassword()));
    }

    @Override
    public UserView registerUser(@Valid UserDTOSignup user) {
        if (!Objects.equals(user.getUsername(), "") && !Objects.equals(user.getPassword(), "")) {
            UserDetails existingUser = userRepo.findByUsername(user.getUsername());
            if (existingUser == null) {
                User newUser  = new User(user.getUsername(), encoder.encode(user.getPassword()), user.getFullName(), user.getNif(), user.getMorada());
                newUser.addAuthority(new Role(Role.User));
                userRepo.saveUser(newUser);
                return new UserView(newUser.getUserId(), newUser.getUsername(), newUser.getFullName(), newUser.getAuthorities());
            } else {
                throw new IllegalArgumentException("User already exists");
            }
        } else {
            throw new IllegalArgumentException("Username and password must not be empty");
        }
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public void saveUser(User user) {
        userRepo.saveUser(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepo.deleteUser(username);
    }

    @Override
    public boolean changePassword(String user, UserDTOPasswordChange password) {
        return userRepo.changePassword(user, password);
    }
}
