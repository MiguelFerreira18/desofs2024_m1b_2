package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.PacoteMapper.PacoteMapper;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacote")
public class PacoteController {
    @Autowired
    private PacoteService pacoteService;
    @Autowired
    private PacoteMapper pacoteMapper;
    @Autowired
    private PersonalValidation validation;


    @PostMapping("/save")
    public ResponseEntity<PacoteDTOResponse> savePacote(@RequestBody PacoteDTOSaveRequest pacote) {
        if (!validation.validate(pacote)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            PacoteDTOServiceRequest pacoteRequestService = pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacote);
            if (!validation.validate(pacoteRequestService)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            PacoteDTOServiceResponse pacoteServiceResponse = pacoteService.save(pacoteRequestService);
            return ResponseEntity.ok( pacoteMapper.fromPacoteToDto(pacoteServiceResponse));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{pacoteId}")
    public ResponseEntity<PacoteDTOResponse> getPacote(@PathVariable Long pacoteId) {
        if (pacoteId < 0) return ResponseEntity.badRequest().build();
        try {

            PacoteDTOServiceResponse pacoteServiceResponse = pacoteService.findbyId(pacoteId);
            return ResponseEntity.ok( pacoteMapper.fromPacoteToDto(pacoteServiceResponse));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<PacoteDTOResponse> updatePacote(@RequestBody PacoteDTOPatchRequest pacote) {
        if (!validation.validate(pacote)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            PacoteDTOServicePatchRequest pacoteRequestService = pacoteMapper.toPacoteDTOServicePAtchRequestFromPacoteDTOPatchRequest(pacote);
            if (!validation.validate(pacoteRequestService)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            PacoteDTOServiceResponse pacoteServiceResponse = pacoteService.update(pacoteRequestService);
            return ResponseEntity.ok( pacoteMapper.fromPacoteToDto(pacoteServiceResponse));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/disable/{pacoteId}")
    public ResponseEntity disablePacote(@PathVariable Long pacoteId) {
        if (pacoteId < 0) return ResponseEntity.badRequest().build();
        pacoteService.disable(pacoteId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/enable/{pacoteId}")
    public ResponseEntity enablePacote(@PathVariable Long pacoteId) {
        if (pacoteId < 0) return ResponseEntity.badRequest().build();
        pacoteService.enable(pacoteId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{pacoteId}")
    public ResponseEntity deletePacote(@PathVariable Long pacoteId) {
        if (pacoteId < 0) return ResponseEntity.badRequest().build();
        pacoteService.deleteById(pacoteId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PacoteDTOResponse>> getAllPacotes() {
        try {
            List<PacoteDTOResponse> pacotes = pacoteMapper.fromPacoteDtoServiceResponseListToPacoteDToResponseList(pacoteService.findAll());
            return ResponseEntity.ok(pacotes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
