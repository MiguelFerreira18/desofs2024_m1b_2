package isep.ipp.pt.api.desofs.Config;

import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LocalLogs;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogsConfig {

    @Value("${app.logger.strategy}")
    private String loggerStrategy;

    @Bean
    public LoggerStrategy loggerStrategy() {
        if (loggerStrategy.equals("prod")) {
            return new DatabaseLogger();
        } else {
            return new LocalLogs();
        }
    }
}
