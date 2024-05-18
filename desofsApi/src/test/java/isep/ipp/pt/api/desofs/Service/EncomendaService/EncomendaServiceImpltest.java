package isep.ipp.pt.api.desofs.Service.EncomendaService;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Model.Encomenda;
import isep.ipp.pt.api.desofs.Model.Estado;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.EncomendaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EncomendaServiceImpltest {
    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    @Autowired
    private UserServiceRepo userRepo;

    @Autowired
    private EncomendaServiceRepo encomendaRepo;

    @Autowired
    private EncomendaService encomendaService;

    private Validator validator;
    @BeforeEach
    public void populate() {
        encomendaRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        TipoPacote tipoPacote = new TipoPacote("Mediterraneo");
        TipoPacote saved = tipoPacoteRepo.save(tipoPacote);
        Pacote pacote = new Pacote(1L,"pacote", 10.0, "pacotedescription", true , saved);
        pacoteRepo.save(pacote);
        User admin = new User(1L, "admin@mail.com", "adminpw1", "josé", randomNum+"" , "RUA cena");
        admin.addAuthority(new Role(Role.Admin));
        userRepo.saveUser(admin);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void clean() {
        encomendaRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 5, 10, pacote, REGISTADO, admin@mail.com,2024-05-07T08:30:00",
            "2, 4, 20, pacote, REGISTADO, admin@mail.com,2024-05-08T08:30:00",
            "3, 3, 30, pacote, REGISTADO, admin@mail.com,2024-05-09T08:30:00",
            "4, 2, 40, pacote, REGISTADO, admin@mail.com,2024-05-10T08:30:00",
            "5, 1, 50, pacote, REGISTADO, admin@mail.com,2024-05-11T08:30:00"
    })
    @Order(1)
    public void testServiceSaveSuccess(int mealsPerWeek, int numberOfPeople, double price, String pacoteName, Estado estado, String userName, LocalDateTime dataEncomenda) {
        Pacote p = pacoteRepo.findbyName(pacoteName);
        User u = userRepo.findByUserByEmail(userName);
        EncomendaDTOServiceRequest encomendaDTOServiceRequest = new EncomendaDTOServiceRequest(mealsPerWeek, numberOfPeople, price, dataEncomenda, p.getPacoteId(),estado,u.getUserId());

        Set<ConstraintViolation<EncomendaDTOServiceRequest>> violations = validator.validate(encomendaDTOServiceRequest);

        EncomendaDTOServiceResponse encomendaResponse = encomendaService.save(encomendaDTOServiceRequest);
        assertNotNull(encomendaResponse);
        assertEquals(encomendaResponse.getDataEncomenda(), encomendaDTOServiceRequest.getDataEncomenda());
        assertEquals(encomendaResponse.getEstado(), encomendaDTOServiceRequest.getEstado());
        assertEquals(encomendaResponse.getMealsPerWeek(), encomendaDTOServiceRequest.getMealsPerWeek());
        assertEquals(encomendaResponse.getNumberOfPeople(), encomendaDTOServiceRequest.getNumberOfPeople());
        assertEquals(encomendaResponse.getPrice(), encomendaDTOServiceRequest.getPrice());

        assertTrue(violations.isEmpty());

        Encomenda enc = encomendaRepo.findByDateUserPackage(u.getUserId(),p.getPacoteId(),dataEncomenda);
        assertNotNull(enc);
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 2, 2, pacote, REGISTADO, admin@mail.com",
            "11, 2, 2, pacote, REGISTADO, admin@mail.com",
            "2, -3, 2, pacote, REGISTADO, admin@mail.com",
            "2, 10, 2, pacote, REGISTADO, admin@mail.com",
            "2, 2, -50, pacote, REGISTADO, admin@mail.com",
    })
    @Order(2)
    public void testServiceSaveInvalid(int mealsPerWeek, int numberOfPeople, double price, String pacoteName, Estado estado, String userName) {
        Pacote p = pacoteRepo.findbyName(pacoteName);
        User u = userRepo.findByUserByEmail(userName);
        EncomendaDTOServiceRequest encomendaDTOServiceRequest = new EncomendaDTOServiceRequest(mealsPerWeek, numberOfPeople, price, LocalDateTime.now(), p.getPacoteId(),estado,u.getUserId());
        Set<ConstraintViolation<EncomendaDTOServiceRequest>> violations = validator.validate(encomendaDTOServiceRequest);
        EncomendaDTOServiceResponse encomendaResponse = null;
        try {
            encomendaResponse = encomendaService.save(encomendaDTOServiceRequest);;
        }catch (Exception e){
            assertNull(encomendaResponse);
        }
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "0",
            "-1234567",
            "-98765432",
            "-12300000",
    })
    public void testServicefindbyIdFail(Long id) {
        EncomendaDTOServiceResponse encomendaResponse = encomendaService.findbyId(id);
        assertNull(encomendaResponse);
    }

    @ParameterizedTest
    @CsvSource({
            "-1234",
            "0",
            "-1",
    })
    public void testServiceDeleteByIdFail(Long id) {
        encomendaService.deleteById(id);
        EncomendaDTOServiceResponse encomendaResponse = encomendaService.findbyId(id);
        assertNull(encomendaResponse);
    }

}
