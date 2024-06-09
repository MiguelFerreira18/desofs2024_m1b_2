package isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTOPasswordChangeRequest {

    @NotBlank
    private static String username;

    @NotBlank
    @Size(min = 12, max = 128, message = "OldPassword must be between 12 and 128 characters")
    private static String oldPassword;
    @NotBlank
    @Size(min = 12, max = 128, message = "NewPassword must be between 12 and 128 characters")
    private static String newPassword;

    public UserDTOPasswordChangeRequest(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;

        validatePassword(oldPassword);
        validatePassword(newPassword);
    }

    private void validatePassword(String password) {
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

    public static @NotBlank @Size(min = 12, max = 128, message = "OldPassword must be between 12 and 128 characters") String getOldPassword() {
        return oldPassword;
    }

    public static @NotBlank @Size(min = 12, max = 128, message = "NewPassword must be between 12 and 128 characters") String getNewPassword() {
        return newPassword;
    }

    public static @NotBlank String getUsername() {
        return username;
    }
}
