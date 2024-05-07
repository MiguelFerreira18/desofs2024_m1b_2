package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacoteControllerTest {
    @Autowired
    private PacoteController pacoteController;

    @Autowired
    private PacoteService pacoteServiceRepo;


    @Autowired
    private TipoPacoteServiceRepo tipoPacoteServiceRepo;



    @BeforeEach
    public void setUp() {
        pacoteServiceRepo.deleteAll();
        TipoPacote tp1 = new TipoPacote(1L,"TugaTube");
        tipoPacoteServiceRepo.save(tp1);
    }


    @AfterEach
    public void tearDown() {
        pacoteServiceRepo.deleteAll();
        tipoPacoteServiceRepo.deleteAll();
    }


    @Test
    @Order(1)
    public void testSavePacote_ValidRequest() {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest("Pacote1", 10.0, "Pacote1 Description", true, 1L);

        ResponseEntity<PacoteDTOResponse> response = pacoteController.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "Pacote1, 10.0, Pacote1 Description, true, 1",
            "Pacote2, 20.0, Pacote2 Description, false, 1",
            "Pacote3, 30.0, Pacote3 Description, true, 1",
            "Pacote4, 40.0, Pacote4 Description, false, 1",
            "Pacote5, 50.0, Pacote5 Description, true, 1",
    })
    @Order(2)
    public void testSavePacote_ValidRequest_Parameterized(String nome, double preco, String descricao, boolean ativo, String pacote) {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest(nome, preco, descricao, ativo, 1L);

        ResponseEntity<PacoteDTOResponse> response = pacoteController.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    public void testSavePacote_InvalidRequest() {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest("a", -1, "Pacote1 Description", true, 1L);

        ResponseEntity<PacoteDTOResponse> response = pacoteController.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "a, -1,x, true, 1",
            "b, -2, y, false, 1",
            "c, -3,, true, 1",
            "d, -4, f, false, 1",
            "e, -5, 8, true, 1",
    })
    @Order(4)
    public void testSavePacote_InvalidRequest_Parameterized(String nome, double preco, String descricao, boolean ativo, String pacote) {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest(nome, preco, descricao, ativo, 1L);

        ResponseEntity<PacoteDTOResponse> response = pacoteController.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }



    @Test
    @Order(7)
    public void testGetPacote_InvalidRequest() {
        ResponseEntity<PacoteDTOResponse> response = pacoteController.getPacote(-1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5",
    })
    @Order(8)
    public void testGetPacote_InvalidRequest_Parameterized(Long id) {
        ResponseEntity<PacoteDTOResponse> response = pacoteController.getPacote(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }


    @Test
    @Order(9)
    public void testDisablePacote_ValidRequest() {
        ResponseEntity response = pacoteController.disablePacote(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5",
    })
    @Order(10)
    public void testDisablePacote_ValidRequest_Parameterized(Long id) {
        ResponseEntity response = pacoteController.disablePacote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }



    @Test
    @Order(11)
    public void testDisablePacote_InvalidRequest() {
        ResponseEntity response = pacoteController.disablePacote(-1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5",
    })
    @Order(12)
    public void testDisablePacote_InvalidRequest_Parameterized(Long id) {
        ResponseEntity response = pacoteController.disablePacote(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    @Order(13)
    public void testEnablePacote_ValidRequest() {
        ResponseEntity response = pacoteController.enablePacote(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }

    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5",
    })
    @Order(14)
    public void testEnablePacote_ValidRequest_Parameterized(Long id) {
        ResponseEntity response = pacoteController.enablePacote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }


    @Test
    @Order(15)
    public void testEnablePacote_InvalidRequest() {
        ResponseEntity response = pacoteController.enablePacote(-1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5",
    })
    @Order(16)
    public void testEnablePacote_InvalidRequest_Parameterized(Long id) {
        ResponseEntity response = pacoteController.enablePacote(id);
        pacoteServiceRepo.deleteAll();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());



    }




}