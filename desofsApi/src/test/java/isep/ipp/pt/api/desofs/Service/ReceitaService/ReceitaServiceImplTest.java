package isep.ipp.pt.api.desofs.Service.ReceitaService;

import isep.ipp.pt.api.desofs.Controllers.ReceitaController;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Model.Encomenda;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import isep.ipp.pt.api.desofs.Model.Receita;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReceitaServiceImplTest {
    @Autowired
    private ReceitaController receitaController;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ReceitaServiceRepo receitaServiceRepo;

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaServiceRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteServiceRepo;

    @Autowired
    private PacoteServiceRepo pacoteServiceRepo;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        receitaServiceRepo.deleteAll();
        tipoReceitaServiceRepo.deleteAll();
        pacoteServiceRepo.deleteAll();
        tipoPacoteServiceRepo.deleteAll();


        TipoPacote tp1 = new TipoPacote( "TipoPacote1");
        tipoPacoteServiceRepo.save(tp1);

        Pacote p1 = new Pacote("Pacote1", 10.0, "Pacote1 Description", true, tp1);
        pacoteServiceRepo.save(p1);

        TipoReceita tr1 = new TipoReceita("TipoReceita1");
        tipoReceitaServiceRepo.save(tr1);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "./Recipes/2024-06-08_19-22-44.pdf, receita1, Pacote1, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receita2, Pacote1, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receita3, Pacote1, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receita4, Pacote1, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receita5, Pacote1, TipoReceita1",
    })
    @Order(1)
    public void testSaveReceita_ValidRequest_Parameterized(String path, String nome, String pacote, String tipoReceita) {
        TipoReceita tr = tipoReceitaServiceRepo.findbyName(tipoReceita);
        Pacote p = pacoteServiceRepo.findbyName(pacote);

        ReceitaDTOServiceRequest receitaDTOServiceRequest = new ReceitaDTOServiceRequest(path,nome,p.getPacoteId(),tr.getTipoReceitaId());

        Set<ConstraintViolation<ReceitaDTOServiceRequest>> violations = validator.validate(receitaDTOServiceRequest);

        ReceitaDTOServiceResponse receitaResponse = receitaService.save(receitaDTOServiceRequest);
        assertNotNull(receitaResponse);
        assertNotNull(receitaResponse.getPath());
        assertEquals(receitaResponse.getTipoReceita().getTipoReceitaId(), receitaDTOServiceRequest.getTipoReceita());
        assertEquals(receitaResponse.getNome() , receitaDTOServiceRequest.getNome());
        assertEquals(receitaResponse.getPacote().getPacoteId(), receitaDTOServiceRequest.getPacote());

        assertTrue(violations.isEmpty());

        Receita r = receitaServiceRepo.findbyId(receitaResponse.getReceitaId());
        assertNotNull(r);
    }

    @ParameterizedTest
    @CsvSource({
            "./Recipes/2024-06-08_19-22-44.zip, receita1, Pacote1, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receitaNo<script>, Pacote1, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receita3, Pacote1NonExistent, TipoReceita1",
            "./Recipes/2024-06-08_19-22-44.pdf, receita4, Pacote1, TipoReceita1NonExistent",
            "./Recipes/2024-06-08_19-22-44.exe, receita5, Pacote1, TipoReceita1",
    })
    @Order(2)
    public void testSaveReceita_InvalidRequest_Parameterized(String path, String nome, String pacote, String tipoReceita) {
        TipoReceita tr = tipoReceitaServiceRepo.findbyName(tipoReceita);
        Pacote p = pacoteServiceRepo.findbyName(pacote);
        ReceitaDTOServiceRequest receitaDTOServiceRequest;
        ReceitaDTOServiceResponse receitaResponse = null;
        try{
            receitaDTOServiceRequest = new ReceitaDTOServiceRequest(path,nome,p.getPacoteId(),tr.getTipoReceitaId());
            Set<ConstraintViolation<ReceitaDTOServiceRequest>> violations = validator.validate(receitaDTOServiceRequest);
            assertFalse(violations.isEmpty());
            receitaResponse = receitaService.save(receitaDTOServiceRequest);
        }catch (Exception e){
            assertNull(receitaResponse);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "0",
            "-1234567",
            "-98765432",
            "-12300000",
    })
    @Order(3)
    public void testServicefindbyIdFail(Long id) {
        ReceitaDTOServiceResponse receitaResponse = receitaService.findbyId(id);
        assertNull(receitaResponse);
    }

    @ParameterizedTest
    @CsvSource({
            "-1234",
            "0",
            "-1",
    })
    @Order(4)
    public void testServiceDeleteByIdFail(Long id) {
        receitaService.deleteById(id);
        ReceitaDTOServiceResponse receitaResponse = receitaService.findbyId(id);
        assertNull(receitaResponse);
    }
}
