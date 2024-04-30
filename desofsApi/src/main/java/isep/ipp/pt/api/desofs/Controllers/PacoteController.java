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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
