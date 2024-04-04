package isep.ipp.pt.api.desofs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesofsApplication {
	@Value("${spring.datasource.url}")
	private static String url;

	@Value("${spring.datasource.username}")
	private static String username;

	@Value("${spring.datasource.password}")
	private static String password;

	private static final Logger logger = LoggerFactory.getLogger(DesofsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DesofsApplication.class, args);
		logger.info("Application started successfully");
		logger.info(url);
		logger.info(username);
		logger.info(password);
		logger.info("Application started successfully");

	}

}
