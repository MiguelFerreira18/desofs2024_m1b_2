package isep.ipp.pt.api.desofs.securityTests;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Estado;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class EncomendaDTOTest {

    private Validator validator;
    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @ParameterizedTest
    @CsvSource(textBlock =
            """
            -12
            0
            -3
            -1
            8
            9
            123
            6431
            """)
    @DisplayName("Test security vulnerabilities for number of meals")
    public void testSecurityVulnerabilitiesForNumberOfMeals(int numberOfMeals) {
        EncomendaDTOSaveRequest response = new EncomendaDTOSaveRequest(numberOfMeals, 2,2, LocalDateTime.now(), 1L, Estado.REGISTADO, 1L);
        Set<ConstraintViolation<EncomendaDTOSaveRequest>> violations = validator.validate(response);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource(textBlock =
            """
            -12
            0
            -23
            -412
            -1
            -99
            """)
    @DisplayName("Test security vulnerabilities for price")
    public void testSecurityVulnerabilitiesForPrice(double price) {
        EncomendaDTOSaveRequest response = new EncomendaDTOSaveRequest(2, 2,price, LocalDateTime.now(), 1L, Estado.REGISTADO, 1L);
        Set<ConstraintViolation<EncomendaDTOSaveRequest>> violations = validator.validate(response);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource(textBlock =
            """
            0
            6
            -1
            -3
            7
            8
            9
            996
            -1325
            """)
    @DisplayName("Test security vulnerabilities for number of people ")
    public void testSecurityVulnerabilitiesForNumberOfPeople(int numberOfPeople) {
        EncomendaDTOSaveRequest response = new EncomendaDTOSaveRequest(2, numberOfPeople, 2, LocalDateTime.now(), 1L, Estado.REGISTADO, 1L);
        Set<ConstraintViolation<EncomendaDTOSaveRequest>> violations = validator.validate(response);
        assertFalse(violations.isEmpty());
    }
    @ParameterizedTest
    @CsvSource(textBlock =
            """
            0
            -6
            -1
            -3
            -7
            -8
            -9
            -996
            -1325
            """)
    @DisplayName("Test security vulnerabilities for Pacote ID")
    public void testSecurityVulnerabilitiesForPacoteID(Long pacoteId) {
        EncomendaDTOSaveRequest response = new EncomendaDTOSaveRequest(2, 2, 2, LocalDateTime.now(), pacoteId, Estado.REGISTADO, 1L);
        Set<ConstraintViolation<EncomendaDTOSaveRequest>> violations = validator.validate(response);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource(textBlock =
            """
            0
            -6
            -1
            -3
            -7
            -8
            -9
            -996
            -1325
            """)
    @DisplayName("Test security vulnerabilities for User ID")
    public void testSecurityVulnerabilitiesForUserId(Long userId) {
        EncomendaDTOSaveRequest response = new EncomendaDTOSaveRequest(2, 2, 2, LocalDateTime.now(),1L, Estado.REGISTADO, userId);
        Set<ConstraintViolation<EncomendaDTOSaveRequest>> violations = validator.validate(response);
        assertFalse(violations.isEmpty());
    }

}