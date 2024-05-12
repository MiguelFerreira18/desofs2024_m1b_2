package isep.ipp.pt.api.desofs.Service.TipoReceitaService;

import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TipoReceitaServiceImplTest {

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaServiceRepo;
    @Autowired
    private TipoReceitaService tipoReceitaService;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        tipoReceitaServiceRepo.deleteAll();

        TipoReceita tr1 = new TipoReceita(1L, "TipoReceita1");
        TipoReceita tr2 = new TipoReceita(2L, "TipoReceita2");
        TipoReceita tr3 = new TipoReceita(3L, "TipoReceita3");
        TipoReceita tr4 = new TipoReceita(4L, "TipoReceita4");
        TipoReceita tr5 = new TipoReceita(5L, "TipoReceita5");
        tipoReceitaServiceRepo.save(tr1);
        tipoReceitaServiceRepo.save(tr2);
        tipoReceitaServiceRepo.save(tr3);
        tipoReceitaServiceRepo.save(tr4);
        tipoReceitaServiceRepo.save(tr5);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown() {
        tipoReceitaServiceRepo.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "TipoReceita1",
            "TipoReceita2",
            "TipoReceita3",
            "TipoReceita4",
            "TipoReceita5"
    })
    public void testSaveTipoReceita_ValidRequest_Parameterized(String tipo) {
        TipoReceitaDTOServiceRequest tipoReceitaDTOServiceRequest = new TipoReceitaDTOServiceRequest(tipo);
        Set<ConstraintViolation<TipoReceitaDTOServiceRequest>> violations = validator.validate(tipoReceitaDTOServiceRequest);
        TipoReceitaDTOServiceResponse receitaDTOServiceResponse = tipoReceitaService.save(tipoReceitaDTOServiceRequest);

        assertEquals(receitaDTOServiceResponse.getNome(),tipoReceitaDTOServiceRequest.getNome());
        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "<script>",
            ".",
            "Tip0Receit@3",
            "<br>TipoReceita4</br>",
            "[-TipoReceita5-]"
    })
    public void testSaveTipoReceita_InvalidRequest_Parameterized(String tipo) {
        TipoReceitaDTOServiceRequest tipoReceitaDTOServiceRequest = new TipoReceitaDTOServiceRequest(tipo);
        Set<ConstraintViolation<TipoReceitaDTOServiceRequest>> violations = validator.validate(tipoReceitaDTOServiceRequest);
        TipoReceitaDTOServiceResponse pacoteDTOServiceResponse = null;
        try {
            pacoteDTOServiceResponse = tipoReceitaService.save(tipoReceitaDTOServiceRequest);
        } catch (Exception e) {
            assertNull(pacoteDTOServiceResponse);
        }
        assertNull(pacoteDTOServiceResponse);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5"
    })
    public void testGetTipoReceita_InvalidRequest_Parameterized(Long tipo) {
        TipoReceitaDTOServiceResponse tipoReceitaDTOServiceResponse = tipoReceitaService.findbyId(tipo);

        assertNull(tipoReceitaDTOServiceResponse);
    }

    @Test
    public void testFindAllTipoReceita_ValidRequest() {
        List<TipoReceitaDTOServiceResponse> tipoReceitaDTOServiceResponseList = tipoReceitaService.findAll();
        TipoReceitaDTOServiceResponse tipoReceitaDTOServiceResponse = tipoReceitaService.findAll().get(0);

        assertNotNull(tipoReceitaDTOServiceResponse);
        assertEquals(tipoReceitaDTOServiceResponseList.size(), 5);
    }

    @Test
    public void testDeleteAllTipoReceita_ValidRequest() {
        tipoReceitaService.deleteAll();
        List<TipoReceitaDTOServiceResponse> tipoReceitaDTOServiceResponseList = tipoReceitaService.findAll();

        assertTrue(tipoReceitaDTOServiceResponseList.isEmpty());
    }
}
