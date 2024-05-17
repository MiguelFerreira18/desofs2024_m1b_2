package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Service.TipoReceitaService.TipoReceitaService;
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
public class TipoReceitaControllerTest {

    @Autowired
    private TipoReceitaController tipoReceitaController;

    @Autowired
    private TipoReceitaService tipoReceitaService;

    @AfterEach
    public void tearDown() {
        tipoReceitaService.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        tipoReceitaService.deleteAll();
    }
    
    @Test
    @Order(1)
    public void testSaveTipoReceita_ValidRequest() {
        TipoReceitaDTOSaveRequest tipoReceitaDTOSaveRequest = new TipoReceitaDTOSaveRequest("TipoReceita1");

        ResponseEntity<TipoReceitaDTOResponse> response = tipoReceitaController.save(tipoReceitaDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "TipoReceita1",
            "TipoReceita2",
            "TipoReceita3",
            "TipoReceita4",
            "TipoReceita5"
    })
    @Order(2)
    public void testSaveTipoReceita_ValidRequest_Parameterized(String nome) {
        TipoReceitaDTOSaveRequest tipoReceitaDTOSaveRequest = new TipoReceitaDTOSaveRequest(nome);

        ResponseEntity<TipoReceitaDTOResponse> response = tipoReceitaController.save(tipoReceitaDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    public void testSaveTipoReceita_InvalidRequest() {
        TipoReceitaDTOSaveRequest tipoReceitaDTOSaveRequest = new TipoReceitaDTOSaveRequest("");

        ResponseEntity<TipoReceitaDTOResponse> response = tipoReceitaController.save(tipoReceitaDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "<script>",
            "Receit@2",
            "<b>Receita3</b>",
            ".",
            "[-]"
    })
    @Order(4)
    public void testSaveTipoReceita_InvalidRequest_Parameterized(String nome) {
        TipoReceitaDTOSaveRequest tipoReceitaDTOSaveRequest = new TipoReceitaDTOSaveRequest(nome);

        ResponseEntity<TipoReceitaDTOResponse> response = tipoReceitaController.save(tipoReceitaDTOSaveRequest);

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
    @Order(5)
    public void testGetTipoReceita_InvalidRequest_Parameterized(Long id) {
        ResponseEntity<TipoReceitaDTOResponse> response = tipoReceitaController.getTipoReceita(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

}
