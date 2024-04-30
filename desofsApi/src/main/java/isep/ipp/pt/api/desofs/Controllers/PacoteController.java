package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.PacoteDTO.ControllerLayer.PacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Mapper.PacoteMapper.PacoteMapper;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import jakarta.validation.Valid;
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
    public ResponseEntity<PacoteDTOResponse> savePacote(@RequestBody PacoteDTOSaveRequest pacote){
        try {
            return ResponseEntity.ok(pacoteMapper.fromPacoteToDto(pacoteService.save(pacoteMapper.toPacoteFromSaveDTO(pacote))));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{pacoteId}")
    public ResponseEntity<PacoteDTOResponse> getPacote(@PathVariable Long pacoteId){
        if(pacoteId < 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(pacoteMapper.fromPacoteToDto(pacoteService.findbyId(pacoteId)));
    }

    @PatchMapping("/disable/{pacoteId}")
    public ResponseEntity disablePacote(@PathVariable Long pacoteId){
        if(pacoteId < 0) return ResponseEntity.badRequest().build();
        pacoteService.disable(pacoteId);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/enable/{pacoteId}")
    public ResponseEntity enablePacote(@PathVariable Long pacoteId){
        if(pacoteId < 0) return ResponseEntity.badRequest().build();
        pacoteService.enable(pacoteId);
        return ResponseEntity.ok().build();
    }

}
