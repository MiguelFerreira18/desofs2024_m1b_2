package isep.ipp.pt.api.desofs.Utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonalValidationTest {

    private PersonalValidation personalValidation;

    @BeforeEach
    public void setup() {
        personalValidation = new PersonalValidation();
    }

    @Test
    public void testValidateWithNoViolations() {
        // Create a valid object with all fields adhering to validation rules
        TestClass validPerson = new TestClass("John Doe", "jhon@gmail.com", 25, "123 Main St.");
        boolean isValid = personalValidation.validate(validPerson);
        assertTrue(isValid);
    }

    @Test
    public void testValidateWithViolations() {
        // Create an invalid object with some fields not adhering to validation rules
        TestClass invalidPerson = new TestClass("", " ", -1, "123 Main St.");
        boolean isValid = personalValidation.validate(invalidPerson);
        assertFalse(isValid);

    }

    @ParameterizedTest
    @CsvSource("""
            ,jhon@gmail.com,25,123 Main St.
            dfcfjyvghvhvasd asdkjnbc jkhebfhbvuehjyfb,jhon@gmail.com,25,123 Main St.
            <><>90908,jhon@gmail.com,25,123 Main St.
            <script>alert('hi')</script>,jhon@gmail.com,25,123 Main St.
            ' OR 1=1;--,jhon@gmail.com,25,123 Main St.
            """)
    public void testValidateForBadUserName(String name, String email, int age, String address) {
        TestClass invalidPerson = new TestClass(name, email, age, address);
        boolean isValid = personalValidation.validate(invalidPerson);
        assertFalse(isValid);
    }

    @ParameterizedTest
    @CsvSource("""
            John Doe,jhongmail.com,25,123 Main St.
            John Doe,jhon@gmailcom,25,123 Main St.
            John Doe,jhongmailcom,25,123 Main St.
            John Doe,jhon@gmailcomjbkncu8iyhg8ghpuib 807bn b,25,123 Main St.
            John Doe,jhon@gmail.com<script>alert('hi')</script>,25,123 Main St.
            """)
    public void testValidateForBadEmail(String name, String email, int age, String address) {
        TestClass invalidPerson = new TestClass(name, email, age, address);
        boolean isValid = personalValidation.validate(invalidPerson);
        assertFalse(isValid);
    }

    @ParameterizedTest
    @CsvSource("""
            John Doe,jhongmail.com,-135,123 Main St.
            John Doe,jhon@gmailcom,-1,123 Main St.
            John Doe,jhongmailcom,-42,123 Main St.
            John Doe,jhon@gmailcomjbkncu8iyhg8ghpuib 807bn b,1245,123 Main St.
            John Doe,jhon@gmail.com<script>alert('hi')</script>,140,123 Main St.
            """)
    public void testValidateForBadAge(String name, String email, int age, String address) {
        TestClass invalidPerson = new TestClass(name, email, age, address);
        boolean isValid = personalValidation.validate(invalidPerson);
        assertFalse(isValid);
    }




    private class TestClass {
        @NotNull
        @NotBlank
        @Size(min = 1, max = 16)
        @Pattern(regexp = "^[a-zA-Z9 ]*$",message = "Name must contain only letters and numbers")
        private String name;
        @Email
        @Size(min = 1, max = 32)
        @Pattern(regexp = "^[a-zA-Z0-9@.]*$",message = "Email must contain only letters, numbers, @ and .")
        private String email;
        @Min(value = 0)
        @Max(value = 120)
        private int age;
        @Size(min = 1, max = 32)
        private String address;

        public TestClass(String name, String email, int age, String address) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.address = address;
        }
    }
}