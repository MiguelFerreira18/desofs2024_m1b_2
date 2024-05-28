package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ControllerLayer.TipoReceitaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoReceitaDTO.ServiceLayer.TipoReceitaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.TipoReceitaMapper.TipoReceitaMapper;
import isep.ipp.pt.api.desofs.Service.TipoReceitaService.TipoReceitaService;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoReceita")
public class TipoReceitaController {

    @Autowired
    private TipoReceitaService tipoReceitaService;
    @Autowired
    private TipoReceitaMapper tipoReceitaMapper;
    @Autowired
    private PersonalValidation validation;

    @PostMapping("/save")
    public ResponseEntity<TipoReceitaDTOResponse> save(@Valid  @RequestBody TipoReceitaDTOSaveRequest tipoReceitaRequest) {
        try {
            TipoReceitaDTOServiceRequest tipoReceitaServiceRequest = tipoReceitaMapper.toTipoReceitaDTOServiceRequestFromTipoReceitaDTOSaveRequest(tipoReceitaRequest);
            if (!validation.validate(tipoReceitaServiceRequest)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            TipoReceitaDTOResponse tipoReceitaDTOResponse = tipoReceitaMapper.toTipoReceitaDTOResponseFromTipoReceitaDTOServiceResponse(tipoReceitaService.save(tipoReceitaServiceRequest));
            return ResponseEntity.ok(tipoReceitaDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{tipoReceitaId}")
    public ResponseEntity<TipoReceitaDTOResponse> getTipoReceita(@PathVariable Long tipoReceitaId) {
        if (tipoReceitaId < 0) return ResponseEntity.badRequest().build();
        try {
            TipoReceitaDTOServiceResponse tipoReceitaServiceResponse = tipoReceitaService.findbyId(tipoReceitaId);
            TipoReceitaDTOResponse tipoReceitaDTOResponse = tipoReceitaMapper.toTipoReceitaDTOResponseFromTipoReceitaDTOServiceResponse(tipoReceitaServiceResponse);
            return ResponseEntity.ok(tipoReceitaDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<TipoReceitaDTOResponse>> getTipoReceitaList() {
        try {
            List<TipoReceitaDTOServiceResponse> tipoReceitaServiceResponseList = tipoReceitaService.findAll();
            List<TipoReceitaDTOResponse> tipoReceitaDTOResponseList = tipoReceitaMapper.toTipoReceitaDTOResponseListFromTipoReceitaDTOServiceResponseList(tipoReceitaServiceResponseList);
            return ResponseEntity.ok(tipoReceitaDTOResponseList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/All")
    public ResponseEntity deleteAll() {
        tipoReceitaService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{tipoReceitaId}")
    public ResponseEntity deleteById(@PathVariable Long tipoReceitaId) {
        if (tipoReceitaId < 0) return ResponseEntity.badRequest().build();
        tipoReceitaService.deleteById(tipoReceitaId);
        return ResponseEntity.ok().build();
    }
}
