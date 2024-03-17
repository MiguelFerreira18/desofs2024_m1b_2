package isep.ipp.pt.api.desofs.Config;

import isep.ipp.pt.api.desofs.Repository.Implementation.UserRepoImpl;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories(basePackages = "isep.ipp.pt.api.desofs.Repository")
@EnableTransactionManagement
public class MysqlConfig {


    //Implementations
    @Bean
    public UserServiceRepo mysqlUser(){
        return new UserRepoImpl();
    }

}
