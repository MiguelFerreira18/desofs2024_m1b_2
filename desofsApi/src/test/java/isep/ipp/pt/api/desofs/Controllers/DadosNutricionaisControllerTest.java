package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDtoResponse;
import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Model.Receita;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Service.DadosNutricionais.DadosNutricionaisService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DadosNutricionaisControllerTest {

    @Autowired
    private DadosNutricionaisController dadosNutricionaisController;

    @Autowired
    private ReceitaServiceRepo receitaServiceRepo;

    @Autowired
    private DadosNutricionaisService dadosNutricionaisService;

    @Autowired
    private PacoteServiceRepo pacoteServiceRepo;

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaServiceRepo;

    @AfterEach
    public void tearDown() {
        receitaServiceRepo.deleteAll();
        dadosNutricionaisService.deleteAll();
        tipoReceitaServiceRepo.deleteAll();

    }

    @BeforeEach
    public void setUp() {
        receitaServiceRepo.deleteAll();
        dadosNutricionaisService.deleteAll();
        tipoReceitaServiceRepo.deleteAll();

        TipoReceita tipoReceita = new TipoReceita("Prato");
        tipoReceitaServiceRepo.save(tipoReceita);
        tipoReceita = new TipoReceita("Sobremesa");
        tipoReceitaServiceRepo.save(tipoReceita);
        receitaServiceRepo.save(new Receita(25L, "./folder/subfolder/file.json", "Bacalhau", pacoteServiceRepo.findbyId(1L), tipoReceitaServiceRepo.findbyName("Prato")));
        receitaServiceRepo.save(new Receita(26L, "./path/ficheiro2.json", "Pipo", pacoteServiceRepo.findbyId(1L), tipoReceitaServiceRepo.findbyName("Sobremesa")));
        receitaServiceRepo.save(new Receita(27L, "./path/ficheiro3.json", "Pipo", pacoteServiceRepo.findbyId(1L), tipoReceitaServiceRepo.findbyName("Sobremesa")));


        DadosNutricionais d = new DadosNutricionais(5L, 25L, "100g", "1000", "100", "100", "100", "100", "100");
        dadosNutricionaisService.saveByReceitaId(25L, d);
        d = new DadosNutricionais(6L, 26L, "100g", "1000", "100", "100", "100", "100", "100");
        dadosNutricionaisService.saveByReceitaId(26L, d);
    }

    @Test
    @Transactional
    public void testValid_GetDadosNutricionaisByReceitaId() {
        ResponseEntity<DadosNutricionaisDtoResponse> response = dadosNutricionaisController.getDadosNutricionaisByReceitaId(25L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Transactional
    public void testInvalid_GetDadosNutricionaisByReceitaId() {
        ResponseEntity<DadosNutricionaisDtoResponse> response = dadosNutricionaisController.getDadosNutricionaisByReceitaId(-1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Transactional
    public void testValid_SaveDadosNutricionais() {
        DadosNutricionaisDTOSaveRequest request = new DadosNutricionaisDTOSaveRequest(27L, "100g", "1000", "100", "100", "100", "100", "100");
        ResponseEntity<Void> response = dadosNutricionaisController.saveDadosNutricionais(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Transactional
    public void testInvalid_SaveDadosNutricionais() {
        DadosNutricionaisDTOSaveRequest request = new DadosNutricionaisDTOSaveRequest(-5L, "100g", "1000", "100", "100", "100", "100", "100");
        ResponseEntity<Void> response = dadosNutricionaisController.saveDadosNutricionais(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Transactional
    public void testValid_UpdateDadosNutricionais() {
        DadosNutricionaisDTOSaveRequest request = new DadosNutricionaisDTOSaveRequest(25L, "100g", "1000", "100", "100", "100", "100", "100");
        ResponseEntity<Void> response = dadosNutricionaisController.updateDadosNutricionais(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Transactional
    public void testInvalid_UpdateDadosNutricionais() {
        DadosNutricionaisDTOSaveRequest request = new DadosNutricionaisDTOSaveRequest(147L, "100g", "1000", "100", "100", "100", "100", "100");
        // populate request with invalid data
        ResponseEntity<Void> response = dadosNutricionaisController.updateDadosNutricionais(request);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Transactional
    public void testValid_GetAllDadosNutricionais() {
        ResponseEntity<List<DadosNutricionaisDtoResponse>> response = dadosNutricionaisController.getAllDadosNutricionais();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
