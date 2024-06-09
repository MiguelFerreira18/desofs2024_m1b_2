package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer.UserDTOPasswordChange;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.EncomendaRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.ReviewRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;



public class UserRepoImpl implements UserServiceRepo {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private EncomendaRepo encomendaRepo;
    @Autowired
    private LoggerStrategy logger;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User getUserById(String userId) {
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
        if(!isTesting()) userRepo.findAll().forEach(user -> logger.log(user.copy(encoder).toString()));
        userRepo.deleteAll();
    }

    @Override
    public User saveUser(User user) {
        if(!isTesting()) logger.log(user.copy(encoder).toString());
        return userRepo.save(user);
    }
  
    @Override
    public User validateUser(String username, String password) {
        return userRepo.validateUser(username, password);
    }

    @Override
    public void deleteUser(String username) {
        if(!isTesting()) logger.log(userRepo.findByUsername(username).copy(encoder).toString());
        reviewRepo.deleteReviewsByUserName(username);
        encomendaRepo.deleteEncomendaByUserName(username);
        userRepo.deleteUser(username);
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
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
