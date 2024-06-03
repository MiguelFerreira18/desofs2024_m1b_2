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
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
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


}
