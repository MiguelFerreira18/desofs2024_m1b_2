package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Service.EncomendaService.EncomendaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EncomendaControllerTest {
    @Autowired
    private EncomendaController encomendaController;

    @Autowired
    private EncomendaService encomendaRepo;

    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    @Autowired
    private UserServiceRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

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
        User admin = new User(1L, "admin" + "@mail.com", "adminpw1", "miguel", randomNum+"" , "RUA cena");
        admin.addAuthority(new Role(Role.Admin));
        userRepo.saveUser(admin);
    }

    @AfterEach
    public void clean() {
        encomendaRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
    }

    @Test
    @Order(1)
    public void testSaveEncomenda_ValidRequest() {
        EncomendaDTOSaveRequest encomendaDTOSaveRequest = new EncomendaDTOSaveRequest(5, 4, 50,LocalDateTime.now(), 1L,"Registado",1L);

        ResponseEntity<EncomendaDTOResponse> response = encomendaController.save(encomendaDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
