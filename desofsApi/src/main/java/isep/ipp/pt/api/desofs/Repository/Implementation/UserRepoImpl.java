package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.EncomendaRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.ReviewRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;



public class UserRepoImpl implements UserServiceRepo {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private EncomendaRepo encomendaRepo;

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
    public void deleteUser(String username) {
        reviewRepo.deleteReviewsByUserName(username);
        encomendaRepo.deleteEncomendaByUserName(username);
        userRepo.deleteUser(username);
    }

}
