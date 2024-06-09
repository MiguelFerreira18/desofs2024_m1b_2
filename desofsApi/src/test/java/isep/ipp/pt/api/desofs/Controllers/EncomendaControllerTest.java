package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Estado;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.*;
import isep.ipp.pt.api.desofs.Service.EncomendaService.EncomendaService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EncomendaControllerTest {
    @Autowired
    private EncomendaController encomendaController;

    @Autowired
    private EncomendaServiceRepo encomendaRepo;

    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    @Autowired
    private UserServiceRepo userRepo;
    @Autowired
    private ReviewServiceRepo reviewServiceRepo;
    @Autowired
    private ReceitaServiceRepo receitaServiceRepo;

    @BeforeEach
    public void populate() {
        receitaServiceRepo.deleteAll();
        reviewServiceRepo.deleteAll();
        encomendaRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        TipoPacote tipoPacote = new TipoPacote("Mediterraneo");
        TipoPacote saved = tipoPacoteRepo.save(tipoPacote);
        Pacote pacote = new Pacote(1L,"pacote", 10.0, "pacotedescription", true , saved);
        pacoteRepo.save(pacote);
        User admin = new User("AA", "admin@mail.com", "adminpw1", "josé", randomNum+"" , "RUA cena");
        admin.addAuthority(new Role(Role.Admin));
        userRepo.saveUser(admin);
    }

    @AfterEach
    public void clean() {
        receitaServiceRepo.deleteAll();
        reviewServiceRepo.deleteAll();
        encomendaRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
    }

    @Test
    @Order(1)
    public void testSaveEncomenda_ValidRequest() {
        Pacote p = pacoteRepo.findbyName("pacote");
        User u = userRepo.findByUserByEmail("admin@mail.com");
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(5, 4, 50,LocalDateTime.now(), p.getPacoteId(), Estado.REGISTADO,u.getUserId());

        ResponseEntity<EncomendaDTOResponse> response = encomendaController.save(encomendaDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 4, 50, pacote, REGISTADO, admin@mail.com",
            "8, 4, 50, pacote, REGISTADO, admin@mail.com",
            "-1, 4, 50, pacote, REGISTADO, admin@mail.com"
    })
    @Order(2)
    public void testSaveEncomenda_Invalid_Meal_Request_Parametrized(int mealsPerWeek, int numberOfPeople, double price, String pacoteName, Estado estado, String userName) {
        Pacote p = pacoteRepo.findbyName(pacoteName);
        User u = userRepo.findByUserByEmail(userName);
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(mealsPerWeek, numberOfPeople, price,LocalDateTime.now(), p.getPacoteId(),estado,u.getUserId());

        ResponseEntity<EncomendaDTOResponse> response = encomendaController.save(encomendaDTOSaveRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "2, -4, 50, pacote, REGISTADO, admin@mail.com",
            "2, 0, 50, pacote, REGISTADO, admin@mail.com",
            "2, 8, 50, pacote, REGISTADO, admin@mail.com"
    })
    @Order(3)
    public void testSaveEncomenda_Invalid_People_Request(int mealsPerWeek, int numberOfPeople, double price, String pacoteName, Estado estado, String userName) {
        Pacote p = pacoteRepo.findbyName(pacoteName);
        User u = userRepo.findByUserByEmail(userName);
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(mealsPerWeek, numberOfPeople, price,LocalDateTime.now(), p.getPacoteId(),estado,u.getUserId());

        ResponseEntity<EncomendaDTOResponse> response = encomendaController.save(encomendaDTOSaveRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 2, -50, pacote, REGISTADO, admin@mail.com",
            "2, 2, 0, pacote, REGISTADO, admin@mail.com",
    })
    @Order(4)
    public void testSaveEncomenda_Invalid_Price_Request(int mealsPerWeek, int numberOfPeople, double price, String pacoteName, Estado estado, String userName) {
        Pacote p = pacoteRepo.findbyName(pacoteName);
        User u = userRepo.findByUserByEmail(userName);
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(mealsPerWeek, numberOfPeople, price,LocalDateTime.now(), p.getPacoteId(),estado,u.getUserId());

        ResponseEntity<EncomendaDTOResponse> response = encomendaController.save(encomendaDTOSaveRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @Order(5)
    public void testSaveEncomenda_Invalid_Package_Request() {
        Pacote p = pacoteRepo.findbyName("pacoteInvalido");
        User u = userRepo.findByUserByEmail("admin@mail.com");
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = null ;
        try{
            encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(4, 4, 50,LocalDateTime.now(), p.getPacoteId(),Estado.REGISTADO,u.getUserId());
        }
        catch(Exception e){
            assertNull(encomendaDTOSaveRequest);
        }
    }

    @Test
    @Order(6)
    public void testSaveEncomenda_Invalid_User_Request() {
        Pacote p = pacoteRepo.findbyName("pacote");
        User u = userRepo.findByUserByEmail("adminInvalido@mail.com");
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = null;
        try{
            encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(4, 4, 50,LocalDateTime.now(), p.getPacoteId(),Estado.REGISTADO,u.getUserId());
        }
        catch(Exception e){
            assertNull(encomendaDTOSaveRequest);
        }
    }

    }
