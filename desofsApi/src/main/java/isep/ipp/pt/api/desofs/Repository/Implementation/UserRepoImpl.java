package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;



public class UserRepoImpl implements UserServiceRepo {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public UserDetails findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public User findbyId(Long id) {
        if (userRepo.findById(id).isPresent()) {
            return userRepo.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public User getUserByName(String name) {
        return userRepo.findByUsername(name);
    }

}
