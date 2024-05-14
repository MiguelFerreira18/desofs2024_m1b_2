package isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDTOSignup {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private final String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 12, max = 128, message = "Password must be between 12 and 128 characters")
    private final String password;

    @NotBlank(message = "Nome cannot be blank")
    private final String fullName;

    @NotBlank(message = "Nif cannot be blank")
    private final String nif;

    @NotBlank(message = "Morada cannot be blank")
    private final String morada;

    public UserDTOSignup(String username, String password, String fullName, String nif, String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
    }
}
