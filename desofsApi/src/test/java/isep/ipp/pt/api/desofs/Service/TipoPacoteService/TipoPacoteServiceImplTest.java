package isep.ipp.pt.api.desofs.Service.TipoPacoteService;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.TipoPacoteMapper.TipoPacoteMapper;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.*;
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
class TipoPacoteServiceImplTest {

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteServiceRepo;
    @Autowired
    private TipoPacoteService tipoPacoteService;
    @Autowired
    private ReceitaServiceRepo receitaRepo;


    @Autowired
    private PacoteServiceRepo pacoteServiceRepo;
    @Autowired
    private ReviewServiceRepo reviewServiceRepo;
    @Autowired
    private UserServiceRepo userServiceRepo;
    @Autowired
    private EncomendaServiceRepo encomendaServiceRepo;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        encomendaServiceRepo.deleteAll();
        receitaRepo.deleteAll();
        reviewServiceRepo.deleteAll();
        userServiceRepo.deleteAll();
        pacoteServiceRepo.deleteAll();
        tipoPacoteServiceRepo.deleteAll();

        TipoPacote tp1 = new TipoPacote(1L, "Mediteraneo");
        TipoPacote tp2 = new TipoPacote(2L, "Tropical");
        TipoPacote tp3 = new TipoPacote(3L, "Tuga");
        TipoPacote tp4 = new TipoPacote(4L, "MultiCultural");
        TipoPacote tp5 = new TipoPacote(5L, "Dieta");
        tipoPacoteServiceRepo.save(tp1);
        tipoPacoteServiceRepo.save(tp2);
        tipoPacoteServiceRepo.save(tp3);
        tipoPacoteServiceRepo.save(tp4);
        tipoPacoteServiceRepo.save(tp5);


        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    public void tearDown() {
        receitaRepo.deleteAll();
        pacoteServiceRepo.deleteAll();
        tipoPacoteServiceRepo.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "Mediteraneo",
            "Tropical",
            "Tuga",
            "MultiCultural",
            "Dieta"
    })
    public void testSaveTipoPacoteSuccess(String tipo) {
        tipoPacoteServiceRepo.deleteAll();

        TipoPacoteDTOServiceRequest tipoPacoteDTOServiceRequest = new TipoPacoteDTOServiceRequest(tipo);

        Set<ConstraintViolation<TipoPacoteDTOServiceRequest>> violations = validator.validate(tipoPacoteDTOServiceRequest);

        TipoPacoteDTOServiceResponse pacoteDTOServiceResponse = tipoPacoteService.save(tipoPacoteDTOServiceRequest);

        assertEquals(pacoteDTOServiceResponse.getNome(),tipoPacoteDTOServiceRequest.getNome());
        assertTrue(violations.isEmpty());

    }

    @ParameterizedTest
    @CsvSource({
            "Mediteraneo??___sad2 ii329",
            "Tropicaladsc;l ,;l ,23-0mb02$#> T>E ",
            "Tuga-=35y -=]a[s ;[",
            "<script>alert('XSS')</script>",
            "'); DROP TABLE receita; --"
    })
    public void testSaveTipoPacoteFail(String tipo) {
        tipoPacoteServiceRepo.deleteAll();

        TipoPacoteDTOServiceRequest tipoPacoteDTOServiceRequest = new TipoPacoteDTOServiceRequest(tipo);

        Set<ConstraintViolation<TipoPacoteDTOServiceRequest>> violations = validator.validate(tipoPacoteDTOServiceRequest);
        TipoPacoteDTOServiceResponse pacoteDTOServiceResponse = null;
        try {
            pacoteDTOServiceResponse = tipoPacoteService.save(tipoPacoteDTOServiceRequest);
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
            "-304305890",
            "-48345875",
            "-534568"
    })
    public void testSaveTipoPacoteFailId(Long tipo) {
        TipoPacoteDTOServiceResponse pacoteDTOServiceResponse = tipoPacoteService.findbyId(tipo);

        assertNull(pacoteDTOServiceResponse);
    }

    @Test
    public void testFindAllTipoPacoteSuccess() {
        List<TipoPacoteDTOServiceResponse> tipoPacoteDTOServiceResponseList = tipoPacoteService.findAll();
        TipoPacoteDTOServiceResponse tipoPacoteDTOServiceResponse = tipoPacoteService.findAll().get(0);

        assertNotNull(tipoPacoteDTOServiceResponse);
        assertEquals(tipoPacoteDTOServiceResponseList.size(), 5);
    }

    @Test
    public void testDeleteAllTipoPacoteSuccess() {
        tipoPacoteService.deleteAll();
        List<TipoPacoteDTOServiceResponse> tipoPacoteDTOServiceResponseList = tipoPacoteService.findAll();

        assertTrue(tipoPacoteDTOServiceResponseList.isEmpty());
    }



}