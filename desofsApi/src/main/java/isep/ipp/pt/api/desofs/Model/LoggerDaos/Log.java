package isep.ipp.pt.api.desofs.Model.LoggerDaos;

import java.security.SecureRandom;
import java.util.Random;

public class Log {
  private String message;
  private String date;

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final int RANDOM_STRING_LENGTH = 20;
  private static final Random RANDOM = new SecureRandom();

  public Log() {
  }

  public Log(String message, String date) {
    this.message = message;
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public String getDate() {
    return date;
  }

  private String generateRandomString() {
    StringBuilder sb = new StringBuilder(RANDOM_STRING_LENGTH);
    for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
      sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
    }
    return sb.toString();
  }

  public void shredData() {
    this.message = generateRandomString();
    this.date = generateRandomString();
  }
}
