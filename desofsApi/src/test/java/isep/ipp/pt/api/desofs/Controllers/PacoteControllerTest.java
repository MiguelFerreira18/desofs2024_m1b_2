package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
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
    private PacoteController pacoteService;

    @Test
    @Order(1)
    public void testSavePacote_ValidRequest() {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest("Pacote1", 10.0, "Pacote1 Description", true, null);

        ResponseEntity<PacoteDTOResponse> response = pacoteService.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "Pacote1, 10.0, Pacote1 Description, true, null",
            "Pacote2, 20.0, Pacote2 Description, false, null",
            "Pacote3, 30.0, Pacote3 Description, true, null",
            "Pacote4, 40.0, Pacote4 Description, false, null",
            "Pacote5, 50.0, Pacote5 Description, true, null",
    })
    @Order(2)
    public void testSavePacote_ValidRequest_Parameterized(String nome, double preco, String descricao, boolean ativo, String pacote) {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest(nome, preco, descricao, ativo, null);

        ResponseEntity<PacoteDTOResponse> response = pacoteService.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    public void testSavePacote_InvalidRequest() {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest("a", -1, "Pacote1 Description", true, null);

        ResponseEntity<PacoteDTOResponse> response = pacoteService.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "a, -1,x, true, null",
            "b, -2, y, false, null",
            "c, -3,, true, null",
            "d, -4, f, false, null",
            "e, -5, 8, true, null",
    })
    @Order(4)
    public void testSavePacote_InvalidRequest_Parameterized(String nome, double preco, String descricao, boolean ativo, String pacote) {
        PacoteDTOSaveRequest pacoteDTOSaveRequest = new PacoteDTOSaveRequest(nome, preco, descricao, ativo, null);

        ResponseEntity<PacoteDTOResponse> response = pacoteService.savePacote(pacoteDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    @Order(5)
    public void testGetPacote_ValidRequest() {
        ResponseEntity<PacoteDTOResponse> response = pacoteService.getPacote(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }
    @ParameterizedTest
    @CsvSource({
            "1",
            "2",
            "3",
            "4",
            "5",
    })
    @Order(6)
    public void testGetPacote_ValidRequest_Parameterized(Long id) {
        ResponseEntity<PacoteDTOResponse> response = pacoteService.getPacote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    @Order(7)
    public void testGetPacote_InvalidRequest() {
        ResponseEntity<PacoteDTOResponse> response = pacoteService.getPacote(-1L);

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
        ResponseEntity<PacoteDTOResponse> response = pacoteService.getPacote(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }


    @Test
    @Order(9)
    public void testDisablePacote_ValidRequest() {
        ResponseEntity response = pacoteService.disablePacote(1L);

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
        ResponseEntity response = pacoteService.disablePacote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }



    @Test
    @Order(11)
    public void testDisablePacote_InvalidRequest() {
        ResponseEntity response = pacoteService.disablePacote(-1L);

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
        ResponseEntity response = pacoteService.disablePacote(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());

    }

    @Test
    @Order(13)
    public void testEnablePacote_ValidRequest() {
        ResponseEntity response = pacoteService.enablePacote(1L);

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
        ResponseEntity response = pacoteService.enablePacote(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

    }


    @Test
    @Order(15)
    public void testEnablePacote_InvalidRequest() {
        ResponseEntity response = pacoteService.enablePacote(-1L);

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
        ResponseEntity response = pacoteService.enablePacote(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }


}