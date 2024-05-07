package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import isep.ipp.pt.api.desofs.Service.TipoPacoteService.TipoPacoteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class TipoPacoteControllerTest {

    @Autowired
    private TipoPacoteController tipoPacoteController;

    @Autowired
    private TipoPacoteService tipoPacoteService;

    @AfterEach
    public void tearDown() {
        tipoPacoteService.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        tipoPacoteService.deleteAll();
    }



    @Test
    @Order(1)
    public void testSaveTipoPacote_ValidRequest() {
        TipoPacoteDTOSaveRequest tipoPacoteDTOSaveRequest = new TipoPacoteDTOSaveRequest("TipoPacote1");

        ResponseEntity<TipoPacoteDTOResponse> response = tipoPacoteController.save(tipoPacoteDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "TipoPacote1",
            "TipoPacote2",
            "TipoPacote3",
            "TipoPacote4",
            "TipoPacote5",
    })
    @Order(2)
    public void testSaveTipoPacote_ValidRequest_Parameterized(String nome) {
        TipoPacoteDTOSaveRequest tipoPacoteDTOSaveRequest = new TipoPacoteDTOSaveRequest(nome);

        ResponseEntity<TipoPacoteDTOResponse> response = tipoPacoteController.save(tipoPacoteDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void testSaveTipopacote_InvalidRequest() {
        TipoPacoteDTOSaveRequest tipoPacoteDTOSaveRequest = new TipoPacoteDTOSaveRequest(";.,d,.m,. 90");
        ResponseEntity<TipoPacoteDTOResponse> response = tipoPacoteController.save(tipoPacoteDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "./m,n ;jklns dfkl;n",
            "<script>alert('hi')</script>",
            "10 9-2 9dfs 90-i  fvd;klner timu90 ioj 9..",
            "' OR 1=1;--",
            "/fbvl;, jklmn90[4 knl 8[hou903/kl hj,b ",
    })
    @Order(4)
    public void testSaveTipopacote_InvalidRequest_Parameterized(String name) {
        TipoPacoteDTOSaveRequest tipoPacoteDTOSaveRequest = new TipoPacoteDTOSaveRequest(name);
        ResponseEntity<TipoPacoteDTOResponse> response = tipoPacoteController.save(tipoPacoteDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @Order(5)
    public void testGetTipoPacote_InvalidRequest() {
        ResponseEntity<TipoPacoteDTOResponse> response = tipoPacoteController.getTipoPacote(-1L);

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
    @Order(6)
    public void testGetTipoPacote_InvalidRequest_Parameterized(Long id) {
        ResponseEntity<TipoPacoteDTOResponse> response = tipoPacoteController.getTipoPacote(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }




}