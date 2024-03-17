package isep.ipp.pt.api.desofs.Service.UserService;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


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
}
