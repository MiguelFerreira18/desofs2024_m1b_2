package isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDTOSignup {

    private final String username;
    private final String password;
    private final String fullName;
    private final String nif;
    private final String morada;

    public UserDTOSignup(String username, String password, String fullName, String nif, String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
    }
}
