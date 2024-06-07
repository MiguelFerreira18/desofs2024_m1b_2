package isep.ipp.pt.api.desofs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DesofsApplication {


	public static void main(String[] args) {
		try{
		SpringApplication.run(DesofsApplication.class, args);
		}catch(Exception e){
			SpringApplication.run(DesofsApplication.class, args);
		}
	}

}
