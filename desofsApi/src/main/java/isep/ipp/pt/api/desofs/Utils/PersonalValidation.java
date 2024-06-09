package isep.ipp.pt.api.desofs.Utils;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PersonalValidation {

    private Validator validator;

    public PersonalValidation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    public <T> boolean validate(T object) {
        Set<ConstraintViolation<T>> violations = this.validator.validate(object);
        for (ConstraintViolation<T> violation : violations) {
            System.out.println("Validation error on field: " + violation.getPropertyPath());
            System.out.println("Message: " + violation.getMessage());
        }
        return violations.isEmpty();
    }


}
