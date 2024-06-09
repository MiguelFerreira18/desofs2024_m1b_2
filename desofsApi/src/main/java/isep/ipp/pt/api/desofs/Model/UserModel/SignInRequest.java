package isep.ipp.pt.api.desofs.Model.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignInRequest(
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        String username,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 12, max = 128, message = "Password must be between 12 and 128 characters")
        String password
) {

        public SignInRequest copy(PasswordEncoder encoder){
                return new SignInRequest(username, null);
        }

        @Override
        public String toString() {
                final StringBuilder sb = new StringBuilder("SignInRequest{");
                sb.append("username='").append(username).append('\'');
                sb.append(", password='").append(password).append('\'');
                sb.append('}');
                return sb.toString();
        }



        public SignInRequest {
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
