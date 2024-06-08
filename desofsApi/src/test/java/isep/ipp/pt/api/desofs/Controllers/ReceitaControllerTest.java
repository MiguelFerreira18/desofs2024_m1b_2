package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import isep.ipp.pt.api.desofs.Service.ReceitaService.ReceitaService;
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
public class ReceitaControllerTest {
    
    @Autowired
    private ReceitaController receitaController;

    @Autowired
    private ReceitaService receitaServiceRepo;

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaServiceRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteServiceRepo;

    @Autowired
    private PacoteServiceRepo pacoteServiceRepo;

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
    }

    @Test
    @Order(1)
    public void testSaveReceita_ValidRequest() {
        TipoReceita tr = tipoReceitaServiceRepo.findbyName("TipoReceita1");
        Pacote p = pacoteServiceRepo.findbyName("Pacote1");

        ReceitaDTOSaveRequest receitaDTOSaveRequest = new ReceitaDTOSaveRequest("./Recipes/2024-06-08_19-22-44.pdf","receita1",p.getPacoteId(),tr.getTipoReceitaId());
        ResponseEntity<ReceitaDTOResponse> response = receitaController.saveReceita(receitaDTOSaveRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
      "./folder1/folder2/file-name.extension, receita1, Pacote1, TipoReceita1",
      "./folder1/folder2/folder3/file-name.extension, receita2, Pacote1, TipoReceita1",
      "./folder1/file-name.extension, receita3, Pacote1, TipoReceita1",
      "./folder1/teste/file-name.extension, receita4, Pacote1, TipoReceita1",
      "./folder1/folder2/name.extension, receita5, Pacote1, TipoReceita1",
    })
    @Order(2)
    public void testSaveReceita_ValidRequest_Parameterized(String path, String nome, String pacote, String tipoReceita) {
        TipoReceita tr = tipoReceitaServiceRepo.findbyName(tipoReceita);
        Pacote p = pacoteServiceRepo.findbyName(pacote);

        ReceitaDTOSaveRequest receitaDTOSaveRequest = new ReceitaDTOSaveRequest(path,nome,p.getPacoteId(),tr.getTipoReceitaId());
        ResponseEntity<ReceitaDTOResponse> response = receitaController.saveReceita(receitaDTOSaveRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "invalid, receita1, Pacote1, TipoReceita1",
            "./folder1/folder2/folder3/file-name.extension, InvalidName<script>, Pacote1, TipoReceita1",
            "./folder1/file-name.extension, receita3, <invalidPackage>, TipoReceita1",
            "./folder1/teste/file-name.extension, receita4, Pacote1, <InvalidTypeReceita>",
            "./folder1/teste/file-name.extension, receita5, Pacote1, TipoReceita999",
            "./folder1/teste/file-name.extension, receita6, Pacote999, TipoReceita1",
    })
    @Order(3)
    public void testSaveReceita_InvalidRequest_Parameterized(String path, String nome, String pacote, String tipoReceita) {
        TipoReceita tr = tipoReceitaServiceRepo.findbyName(tipoReceita);
        Pacote p = pacoteServiceRepo.findbyName(pacote);
        ReceitaDTOSaveRequest receitaDTOSaveRequest = null;
        try {
            receitaDTOSaveRequest = new ReceitaDTOSaveRequest(path,nome,p.getPacoteId(),tr.getTipoReceitaId());
        } catch (Exception e) {
            assertNull(receitaDTOSaveRequest);
        }
    }
}
