package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOResponse;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer.EncomendaDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer.EncomendaDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.EncomendaMapper.EncomendaMapper;
import isep.ipp.pt.api.desofs.Service.EncomendaService.EncomendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encomenda")
public class EncomendaController {
    @Autowired
    private EncomendaService encomendaService;

    @Autowired
    private EncomendaMapper encomendaMapper;


    @PostMapping("/save")
    public ResponseEntity<EncomendaDTOResponse> save(@RequestBody EncomendaDTOSaveRequest encomenda) {
        try {
            EncomendaDTOServiceRequest encomendaServiceRequest = encomendaMapper.toEncomendaDtoServiceRequestFromEncomendaDtoSaveRequest(encomenda);
            EncomendaDTOServiceResponse encomendaServiceResponse = encomendaService.save(encomendaServiceRequest);
            EncomendaDTOResponse encomendaDTOResponse = encomendaMapper.fromEncomendaToDto(encomendaServiceResponse);
            return ResponseEntity.ok(encomendaDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{encomendaId}")
    public ResponseEntity<EncomendaDTOResponse> getEncomenda(@PathVariable Long encomendaId) {
        if (encomendaId < 0) return ResponseEntity.badRequest().build();
        try {
            EncomendaDTOServiceResponse encomendaServiceResponse = encomendaService.findbyId(encomendaId);
            EncomendaDTOResponse encomendaDTOResponse = encomendaMapper.fromEncomendaToDto(encomendaServiceResponse);
            return ResponseEntity.ok(encomendaDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<EncomendaDTOResponse>> getHistoricoEncomendas(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(encomendaMapper.fromEncomendaDtoServiceResponseListToEncomendaDToResponseList(encomendaService.findEncHistory(userId)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<EncomendaDTOResponse> updateEncomenda(@RequestBody EncomendaDTOPatchRequest encomenda) {
        try {
            EncomendaDTOServicePatchRequest encomendaRequestService = encomendaMapper.toEncomendaDTOServicePatchRequestFromEncomendaDTOPatchRequest(encomenda);
            EncomendaDTOServiceResponse encomendaServiceResponse = encomendaService.update(encomendaRequestService);
            EncomendaDTOResponse encomendaDTOResponse = encomendaMapper.fromEncomendaToDto(encomendaServiceResponse);
            return ResponseEntity.ok(encomendaDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{encomendaId}")
    public ResponseEntity deleteEncomenda(@PathVariable Long encomendaId) {
        if (encomendaId < 0) return ResponseEntity.badRequest().build();
        encomendaService.deleteById(encomendaId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<EncomendaDTOResponse>> getAllEncomendas(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(encomendaMapper.fromEncomendaDtoServiceResponseListToEncomendaDToResponseList(encomendaService.findAll(userId)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
