package isep.ipp.pt.api.desofs.Utils;

public interface LoggerStrategy {
    public void log(String message);
    public void logAuthentication(String message);
    public void logUnusualBusinessActivity(String message);
}
