package isep.ipp.pt.api.desofs.Utils;

import com.google.cloud.firestore.Firestore;
import isep.ipp.pt.api.desofs.Model.LoggerDaos.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseLogger {
  @Autowired
  Firestore firestore;

  private final String GENERAL_LOG = "logs";
  private final String AUTHENTICATION_LOG_COLLECTION = "auth_tries";
  private final String UNUSUAL_BUSINESS_ACTIVITY = "unusual_business_activity";

  public void log(String message) {
    Log log = new Log(message, LocalDateTime.now().toString());
    firestore.collection(GENERAL_LOG).document().set(log);
    log.shredData();
  }

  public void logAuthentication(String message) {
    Log log = new Log(message, LocalDateTime.now().toString());
    firestore.collection(AUTHENTICATION_LOG_COLLECTION).document().set(log);
    log.shredData();
  }

  public void logUnusualBusinessActivity(String message) {
    Log log = new Log(message, LocalDateTime.now().toString());
    firestore.collection(UNUSUAL_BUSINESS_ACTIVITY).document().set(log);
    log.shredData();
  }

}
