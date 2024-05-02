package isep.ipp.pt.api.desofs.Model.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
}
