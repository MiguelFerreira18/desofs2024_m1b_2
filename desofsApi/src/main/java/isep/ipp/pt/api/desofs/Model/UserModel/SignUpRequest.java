package isep.ipp.pt.api.desofs.Model.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignUpRequest(

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
        String password,

        @NotBlank(message = "Nome cannot be blank")
        String fullName,

        @NotBlank(message = "Nif cannot be blank")
        String nif,

        @NotBlank(message = "Morada cannot be blank")
        String morada

) {

        public SignUpRequest copy(PasswordEncoder encoder){
                return new SignUpRequest(username, null, encoder.encode(fullName), encoder.encode(nif), encoder.encode(morada));
        }

        @Override
        public String toString() {
                final StringBuilder sb = new StringBuilder("SignUpRequest{");
                sb.append("username='").append(username).append('\'');
                sb.append(", password='").append(password).append('\'');
                sb.append(", fullName='").append(fullName).append('\'');
                sb.append(", nif='").append(nif).append('\'');
                sb.append(", morada='").append(morada).append('\'');
                sb.append('}');
                return sb.toString();
        }
}
