package isep.ipp.pt.api.desofs.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


public class LocalLogs implements LoggerStrategy{
    private final static Logger logger = LoggerFactory.getLogger(LocalLogs.class);
    @Override
    public void log(String message) {
    //        logger.info(message);

    }

    @Override
    public void logAuthentication(String message) {
    //        logger.info(message);

    }

    @Override
    public void logUnusualBusinessActivity(String message) {
    //        logger.warn(message);
    }
}
