package isep.ipp.pt.api.desofs.Service.PacoteService;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacoteServiceImplTest {

    @Autowired
    private PacoteServiceRepo pacoteRepo;
    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;
    @Autowired
    private ReceitaServiceRepo receitaRepo;
    @Autowired
    private PacoteService pacoteService;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        receitaRepo.deleteAll();
        pacoteRepo.deleteAll();
        tipoPacoteRepo.deleteAll();

        TipoPacote tp1 = new TipoPacote(1L,"TugaTube");
        TipoPacote tp = tipoPacoteRepo.save(tp1);
        Pacote p1 = new Pacote(1L, "Pacote1", 10.0, "Pacote1 Description", true, tp);
        Pacote p2 = new Pacote(2L, "Pacote2", 10.0, "Pacote2 Description", true, tp);
        Pacote p3 = new Pacote(3L, "Pacote3", 10.0, "Pacote3 Description", true, tp);
        Pacote p4 = new Pacote(4L, "Pacote4", 10.0, "Pacote4 Description", true, tp);
        Pacote p5 = new Pacote(5L, "Pacote5", 10.0, "Pacote5 Description", true, tp);
        pacoteRepo.save(p1);
        pacoteRepo.save(p2);
        pacoteRepo.save(p3);
        pacoteRepo.save(p4);
        pacoteRepo.save(p5);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();


    }

    @AfterEach
    public void tearDown() {
        receitaRepo.deleteAll();
        pacoteRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
    }


    @ParameterizedTest
    @CsvSource({
            "Pacote1, 10.0, Pacote20 Description, true, TugaTube",
            "Pacote2, 20.0, Pacote30 Description, false, TugaTube",
            "Pacote3, 30.0, Pacote3 Description, true, TugaTube",
            "Pacote4, 40.0, Pacote4 Description, false, TugaTube",
            "Pacote5, 50.0, Pacote5 Description, true, TugaTube",
    })
    @Order(1)
    public void testServiceSaveSuccess(String nome, double preco, String descricao, boolean ativo, String tipoPacote) {
        pacoteRepo.deleteAll();
        TipoPacote tp1 = tipoPacoteRepo.findbyName(tipoPacote);
        PacoteDTOServiceRequest pacoteDTOServiceRequest = new PacoteDTOServiceRequest(nome, preco, descricao, ativo, tp1.getTipoPacoteId());

        Set<ConstraintViolation<PacoteDTOServiceRequest>> violations = validator.validate(pacoteDTOServiceRequest);


        PacoteDTOServiceResponse pacoteResponse = pacoteService.save(pacoteDTOServiceRequest);
        assertNotNull(pacoteResponse);
        assertEquals(pacoteResponse.getNome(), pacoteDTOServiceRequest.getNome());
        assertEquals(pacoteResponse.getPacoteBasePrice(), pacoteDTOServiceRequest.getPacoteBasePrice());
        assertEquals(pacoteResponse.getPacoteDescription(), pacoteDTOServiceRequest.getPacoteDescription());

        Pacote pacote = pacoteRepo.findbyName(pacoteDTOServiceRequest.getNome());
        assertNotNull(pacote);
        assertTrue(violations.isEmpty());

    }

    @ParameterizedTest
    @CsvSource({
            "asxczxasssssssssssssssssssssssssssssssssssssfsdfvsvsdvsdvsvghtfrhc, 10.0, Pacote1 Description, true, 1",
            "Pacvcbasdxzczxcasdoadddddddddddddddddddddddddddte2, 20.0, Pacote2 Dasd acv 23t5t2234escription, false, 1",
            "Pac4a wsf568ad7ote3, 30.0, Pacote3}}++-- Description, true, 1",
            "Pasac Vscote4, 40.0, Pacote4 asd asd <>Description, false, 1",
            "PacZ<<><ote5, -1.0, Pacote5 Descri 289tu89fjuew98mvfc8v2nv n89 f892 89r2nnption, true, 1",
    })
    @Order(2)
    public void testServiceSaveFail(String nome, double preco, String descricao, boolean ativo, Long tipoPacoteId) {
        PacoteDTOServiceRequest pacoteDTOServiceRequest = new PacoteDTOServiceRequest(nome, preco, descricao, ativo, tipoPacoteId);
        Set<ConstraintViolation<PacoteDTOServiceRequest>> violations = validator.validate(pacoteDTOServiceRequest);
        PacoteDTOServiceResponse pacoteResponse= null;
        try {
         pacoteResponse = pacoteService.save(pacoteDTOServiceRequest);
        }catch (Exception e){
            assertNull(pacoteResponse);

        }
        assertFalse(violations.isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
            "-6",
            "-7",
            "-23849",
            "-34525679",
            "-100000000",
    })
    @Order(3)
    public void testServicefindbyIdFail(Long id) {
        PacoteDTOServiceResponse pacoteResponse = pacoteService.findbyId(id);
        assertNull(pacoteResponse);

    }

    @ParameterizedTest
    @CsvSource({
            "Pacote1",
            "Pacote2",
            "Pacote3",
            "Pacote4",
            "Pacote5",
    })
    @Order(4)
    public void testServiceDisableSuccess(String name) {
        Pacote pacoteResponse = pacoteRepo.findbyName(name);
        pacoteService.disable(pacoteResponse.getPacoteId());
        Pacote pacote = pacoteRepo.findbyId(pacoteResponse.getPacoteId());
        assertNotNull(pacote);
        assertTrue(pacote.isDisabled());
    }

    @ParameterizedTest
    @CsvSource({
            "-6",
            "-7",
            "999999",
            "999999999",
            "-100000000",
    })
    @Order(5)
    public void testServiceDisableFail(Long id) {
        pacoteService.disable(id);
        Pacote pacoteResponse = pacoteRepo.findbyId(id);
        assertNull(pacoteResponse);
    }

    @ParameterizedTest
    @CsvSource({
            "Pacote1",
            "Pacote2",
            "Pacote3",
            "Pacote4",
            "Pacote5",
    })
    @Order(6)
    public void testServiceEnableSuccess(String name) {
        Pacote pacoteResponse = pacoteRepo.findbyName(name);
        pacoteService.enable(pacoteResponse.getPacoteId());
        Pacote pacote = pacoteRepo.findbyId(pacoteResponse.getPacoteId());
        assertNotNull(pacote);
        assertFalse(pacote.isDisabled());
    }

    @ParameterizedTest
    @CsvSource({
            "-6",
            "-7",
            "999999",
            "999999999",
            "-100000000",
    })
    @Order(7)
    public void testServiceEnableFail(Long id) {
        pacoteService.enable(id);
        Pacote pacoteResponse = pacoteRepo.findbyId(id);
        assertNull(pacoteResponse);
    }

    @Test
    @Order(8)
    public void testServiceFindAll() {
        List<PacoteDTOServiceResponse> pacotes = pacoteService.findAll();
        assertFalse(pacotes.isEmpty());
        assertEquals(5, pacotes.size());
    }

    @Test
    @Order(9)
    public void testServiceDeleteAll() {
        pacoteService.deleteAll();
        List<Pacote> pacotes = pacoteRepo.findAll();
        assertTrue(pacotes.isEmpty());
    }


}