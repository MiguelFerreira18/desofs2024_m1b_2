package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ControllerLayer.ReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.ReceitaDTO.ServiceLayer.ReceitaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.ReceitaMapper.ReceitaMapper;
import isep.ipp.pt.api.desofs.Service.ReceitaService.ReceitaService;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/receita")
public class ReceitaController {
    @Autowired
    private ReceitaService receitaService;
    @Autowired
    private ReceitaMapper receitaMapper;
    @Autowired
    private PersonalValidation validation;


    @PostMapping("/save")
    public ResponseEntity<ReceitaDTOResponse> saveReceita(@RequestBody ReceitaDTOSaveRequest receita) {
        if (!validation.validate(receita)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            ReceitaDTOServiceRequest receitaRequestService = receitaMapper.toReceitaDtoServiceRequestFromReceitaDtoSaveRequest(receita);
            if (!validation.validate(receitaRequestService)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ReceitaDTOServiceResponse receitaServiceResponse = receitaService.save(receitaRequestService);
            return ResponseEntity.ok( receitaMapper.fromReceitaToDto(receitaServiceResponse));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{receitaId}")
    public ResponseEntity<ReceitaDTOResponse> getReceita(@PathVariable Long receitaId) {
        if (receitaId < 0) return ResponseEntity.badRequest().build();
        try {

            ReceitaDTOServiceResponse receitaServiceResponse = receitaService.findbyId(receitaId);
            return ResponseEntity.ok( receitaMapper.fromReceitaToDto(receitaServiceResponse));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ReceitaDTOResponse> updateReceita(@RequestBody ReceitaDTOPatchRequest receita) {
        if (!validation.validate(receita)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            ReceitaDTOServicePatchRequest receitaRequestService = receitaMapper.toReceitaDTOServicePAtchRequestFromReceitaDTOPatchRequest(receita);
            if (!validation.validate(receitaRequestService)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ReceitaDTOServiceResponse receitaServiceResponse = receitaService.update(receitaRequestService);
            return ResponseEntity.ok( receitaMapper.fromReceitaToDto(receitaServiceResponse));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{receitaId}")
    public ResponseEntity deleteReceita(@PathVariable Long receitaId) {
        if (receitaId < 0) return ResponseEntity.badRequest().build();
        receitaService.deleteById(receitaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReceitaDTOResponse>> getAllReceitas() {
        try {
            List<ReceitaDTOResponse> receitas = receitaMapper.fromReceitaDtoServiceResponseListToReceitaDToResponseList(receitaService.findAll());
            return ResponseEntity.ok(receitas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
