package isep.ipp.pt.api.desofs.Config;

import isep.ipp.pt.api.desofs.DesofsApplication;
import isep.ipp.pt.api.desofs.Repository.Implementation.*;
import isep.ipp.pt.api.desofs.Repository.Interface.*;
import isep.ipp.pt.api.desofs.Repository.Implementation.PacoteServiceImpl;
import isep.ipp.pt.api.desofs.Repository.Implementation.ReviewServiceImpl;
import isep.ipp.pt.api.desofs.Repository.Implementation.TipoPacoteServiceImpl;
import isep.ipp.pt.api.desofs.Repository.Implementation.UserRepoImpl;
import isep.ipp.pt.api.desofs.Repository.Implementation.EncomendaServiceImpl;
import isep.ipp.pt.api.desofs.Repository.Interface.EncomendaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReviewServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Implementation.*;
import isep.ipp.pt.api.desofs.Repository.Interface.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories(basePackages = "isep.ipp.pt.api.desofs.Repository")
@EnableTransactionManagement
public class MysqlConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private static final Logger logger = LoggerFactory.getLogger(MysqlConfig.class);


    //Implementations
    @Bean
    public UserServiceRepo mysqlUser(){
        return new UserRepoImpl();
    }
    @Bean
    public PacoteServiceRepo mysqlPacote(){
        return new PacoteServiceImpl();
    }
    @Bean
    public DadosNutricionaisServiceRepo mysqlDadosNutricionais(){
        return new DadosNutricionaisRepoImpl();
    }
    @Bean
    public ReceitaServiceRepo mysqlReceita(){
        return new ReceitaRepoImpl();
    }
    @Bean
    public TipoReceitaServiceRepo mysqlTipoReceita(){
        return new TipoReceitaRepoImpl();
    }
    @Bean
    public TipoPacoteServiceRepo mysqlTipoPacote(){
        return new TipoPacoteServiceImpl();
    }
    @Bean
    public EncomendaServiceRepo mysqlEncomenda(){
        return new EncomendaServiceImpl();
    }
    @Bean
    public ReviewServiceRepo mysqlReview(){
        return new ReviewServiceImpl();
    }
    @Bean
    public TipoReceitaServiceRepo mysqlTipoReceita(){
        return new TipoReceitaServiceImpl();
    }
    @Bean
    public ReceitaServiceRepo mysqlReceita(){
        return new ReceitaServiceImpl();
    }

    //Datasource
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
