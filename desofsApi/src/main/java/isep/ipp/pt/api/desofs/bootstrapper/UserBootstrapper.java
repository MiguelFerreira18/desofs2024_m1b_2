package isep.ipp.pt.api.desofs.bootstrapper;

import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Implementation.UserRepoImpl;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserBootstrapper implements CommandLineRunner {
    @Autowired
    private UserServiceRepo userRepo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepo.findByUsername("admin1@mail.com") == null){
            User admin1 = new User("admin1@mail.com",encoder.encode("AKQZ`'~{4gf6)VXv}3"),"miguel","123456789","RUA cena");

            admin1.addAuthority(new Role(Role.Admin));
            userRepo.saveUser(admin1);

        }

//        if(userRepo.findByUsername("admin2mail.com") == null){
//            User admin1 = new User("admin2@mail.com",encoder.encode("123456789?qwE"),"rodrigo","987654321","RUA das moradas");
//
//            admin1.addAuthority(new Role(Role.Admin));
//            userRepo.saveUser(admin1);
//
//        }
    }
}
