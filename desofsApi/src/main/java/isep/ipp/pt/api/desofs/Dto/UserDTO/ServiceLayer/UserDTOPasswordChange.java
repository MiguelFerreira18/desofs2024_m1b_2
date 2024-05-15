package isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserDTOPasswordChange {

    @NotBlank
    @Size(min = 12, max = 128, message = "OldPassword must be between 12 and 128 characters")
    private final String oldPassword;
    @NotBlank
    @Size(min = 12, max = 128, message = "NewPassword must be between 12 and 128 characters")
    private final String newPassword;

    public UserDTOPasswordChange(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;

        validatePassword(oldPassword);
        validatePassword(newPassword);
    }

    private void validatePassword(String password) {
        System.out.println(password);
        password = password.replaceAll("\\s+", "");

        if (!password.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Password must contain at least one digit");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character");
        }
    }
}
