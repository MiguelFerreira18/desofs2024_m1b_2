package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.DadosNutricionaisDTO.ControllerLayer.DadosNutricionaisDtoResponse;
import isep.ipp.pt.api.desofs.Mapper.DadosNutricionais.DadosNutricionaisMapper;
import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Service.DadosNutricionais.DadosNutricionaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dadosNutricionais")
public class DadosNutricionaisController {
    @Autowired
    private DadosNutricionaisService dadosNutricionaisService;

    @Autowired
    private DadosNutricionaisMapper mapper;

    @GetMapping("/info/{receitaId}")
    public ResponseEntity<DadosNutricionaisDtoResponse> getDadosNutricionaisByReceitaId(@PathVariable Long receitaId) {
        if (receitaId < 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(mapper.fromDadosNutricionaisToDadosNutricionaisDtoResponse(dadosNutricionaisService.getDadosNutricionaisByReceitaId(receitaId)));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveDadosNutricionais(@RequestBody DadosNutricionaisDTOSaveRequest dadosNutricionaisDtoResponse) {
        //Mapper nao sei porque nao funciona
//        DadosNutricionais d = mapper.fromDadosNutricionaisDtoResponseToDadosNutricionaisDTO(dadosNutricionaisDtoResponse);
        DadosNutricionais d1 = new DadosNutricionais(27L, dadosNutricionaisDtoResponse.getReceitaId(), dadosNutricionaisDtoResponse.getPorcao(), dadosNutricionaisDtoResponse.getValorEnergetico(), dadosNutricionaisDtoResponse.getCarboidratos(), dadosNutricionaisDtoResponse.getProteinas(), dadosNutricionaisDtoResponse.getGordura(), dadosNutricionaisDtoResponse.getSal(), dadosNutricionaisDtoResponse.getAcucar());
        if (d1.getReceitaId() < 0) return ResponseEntity.badRequest().build();
        boolean save = dadosNutricionaisService.saveByReceitaId(d1.getReceitaId(), d1);
        if(save) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateDadosNutricionais(@RequestBody DadosNutricionaisDTOSaveRequest dadosNutricionaisDtoResponse) {
        DadosNutricionais d = new DadosNutricionais(25L, dadosNutricionaisDtoResponse.getReceitaId(), dadosNutricionaisDtoResponse.getPorcao(), dadosNutricionaisDtoResponse.getValorEnergetico(), dadosNutricionaisDtoResponse.getCarboidratos(), dadosNutricionaisDtoResponse.getProteinas(), dadosNutricionaisDtoResponse.getGordura(), dadosNutricionaisDtoResponse.getSal(), dadosNutricionaisDtoResponse.getAcucar());
        if (d.getReceitaId() < 0) return ResponseEntity.badRequest().build();
        boolean update = dadosNutricionaisService.updateByReceitaId(d.getReceitaId(), d);
        if (update) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DadosNutricionaisDtoResponse>> getAllDadosNutricionais() {
        List<DadosNutricionais> dadosNutricionais = dadosNutricionaisService.getAllDadosNutricionais();
        return ResponseEntity.ok(mapper.fromDadosNutricionaisListToDadosNutricionaisDtoResponseList(dadosNutricionais));
    }
}
