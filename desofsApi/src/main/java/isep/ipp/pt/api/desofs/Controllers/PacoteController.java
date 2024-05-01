package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ServiceLayer.PacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.PacoteMapper.PacoteMapper;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacote")
public class PacoteController {
    @Autowired
    private PacoteService pacoteService;
    @Autowired
    private PacoteMapper pacoteMapper;


    @PostMapping("/save")
    public ResponseEntity<PacoteDTOResponse> savePacote(@RequestBody PacoteDTOSaveRequest pacote) {
        try {
            PacoteDTOServiceRequest pacoteRequestService = pacoteMapper.toPacoteDtoServiceRequestFromPacoteDtoSaveRequest(pacote);
            PacoteDTOServiceResponse pacoteServiceResponse = pacoteService.save(pacoteRequestService);
            PacoteDTOResponse pacoteDTOResponse = pacoteMapper.fromPacoteToDto(pacoteServiceResponse);
            return ResponseEntity.ok(pacoteDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{pacoteId}")
    public ResponseEntity<PacoteDTOResponse> getPacote(@PathVariable Long pacoteId) {
        if (pacoteId < 0) return ResponseEntity.badRequest().build();
        try {
            PacoteDTOServiceResponse pacoteServiceResponse = pacoteService.findbyId(pacoteId);
            PacoteDTOResponse pacoteDTOResponse = pacoteMapper.fromPacoteToDto(pacoteServiceResponse);
            return ResponseEntity.ok(pacoteDTOResponse);
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

}
