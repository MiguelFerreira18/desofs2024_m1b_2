package isep.ipp.pt.api.desofs.Model.UserModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 12, max = 128, message = "Password must be between 12 and 128 characters")
        String password,

        @NotBlank(message = "Nome cannot be blank")
        String fullName,

        @NotBlank(message = "Nif cannot be blank")
        String nif,

        @NotBlank(message = "Morada cannot be blank")
        String morada

) {

}
