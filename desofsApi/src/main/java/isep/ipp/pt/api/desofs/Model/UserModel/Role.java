package isep.ipp.pt.api.desofs.Model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
@AllArgsConstructor
public class Role implements GrantedAuthority {


    public static final String Admin = "Admin";
    public static final String GestorFicheiros = "GestorFicheiros";
    public static final String User = "User";
    private String authority;
}
