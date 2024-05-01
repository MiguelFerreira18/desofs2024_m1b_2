package isep.ipp.pt.api.desofs.Service.UserService;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOSignup;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserServiceRepo userRepo;

    @Override
    public User getUserById(Long userId) {
        return userRepo.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public UserDetails findByUsername(String username) {
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Override
    public User validateUser(User user) {
        if(user.getUsername() == null || user.getPassword() == null) return null;
        System.out.println(encoder.encode(user.getPassword()));
        return userRepo.validateUser(user.getUsername(), encoder.encode(user.getPassword()));
    }

    @Override
    public UserView registerUser(UserDTOSignup user) {
        if (user.getUsername() != null || user.getPassword() != null) {
            UserDetails existingUser = userRepo.findByUsername(user.getUsername());
            if (existingUser == null) {
                User newUser = userRepo.saveUser(new User(user.getUsername(), encoder.encode(user.getPassword()), user.getNome(), user.getNif(), user.getMorada()));
                return new UserView(newUser.getUserId(), newUser.getUsername(), newUser.getFullName());
            }
        }
        return null;
    }
}
