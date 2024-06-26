package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ControllerLayer.TipoPacoteDTOResponse;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceRequest;
import isep.ipp.pt.api.desofs.Dto.TipoPacoteDTO.ServiceLayer.TipoPacoteDTOServiceResponse;
import isep.ipp.pt.api.desofs.Mapper.TipoPacoteMapper.TipoPacoteMapper;
import isep.ipp.pt.api.desofs.Service.TipoPacoteService.TipoPacoteService;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoPacote")
public class TipoPacoteController {
    @Autowired
    TipoPacoteService tipoPacoteService;
    @Autowired
    TipoPacoteMapper tipoPacoteMapper;

    @Autowired
    private PersonalValidation validation;
    @Autowired
    private LoggerStrategy logger;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;


    @PostMapping("/save")
    public ResponseEntity<TipoPacoteDTOResponse> save(@Valid  @RequestBody TipoPacoteDTOSaveRequest tipoPacoteRequest) {
        try {
            TipoPacoteDTOServiceRequest tipoPacoteServiceRequest = tipoPacoteMapper.toTipoPacoteDTOServiceRequestFromTipoPacoteDTOSaveRequest(tipoPacoteRequest);
            if (!validation.validate(tipoPacoteServiceRequest)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            TipoPacoteDTOResponse tipoPacoteDTOResponse = tipoPacoteMapper.toTipoPacoteDTOResponseFromTipoPacoteDTOServiceResponse(tipoPacoteService.save(tipoPacoteServiceRequest));
            return ResponseEntity.ok(tipoPacoteDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(!isTesting()) logger.logUnusualBusinessActivity("Error saving tipoPacote" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{tipoPacoteId}")
    public ResponseEntity<TipoPacoteDTOResponse> getTipoPacote(@PathVariable Long tipoPacoteId) {
        if (tipoPacoteId < 0) return ResponseEntity.badRequest().build();
        try {
            TipoPacoteDTOServiceResponse tipoPacoteServiceResponse = tipoPacoteService.findbyId(tipoPacoteId);
            TipoPacoteDTOResponse tipoPacoteDTOResponse = tipoPacoteMapper.toTipoPacoteDTOResponseFromTipoPacoteDTOServiceResponse(tipoPacoteServiceResponse);
            return ResponseEntity.ok(tipoPacoteDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(!isTesting()) logger.logUnusualBusinessActivity("Error getting tipoPacote" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<TipoPacoteDTOResponse>> getTipoPacoteList() {
        try {
            List<TipoPacoteDTOServiceResponse> tipoPacoteServiceResponseList = tipoPacoteService.findAll();
            List<TipoPacoteDTOResponse> tipoPacoteDTOResponseList = tipoPacoteMapper.toTipoPacoteDTOResponseListFromTipoPacoteDTOServiceResponseList(tipoPacoteServiceResponseList);
            return ResponseEntity.ok(tipoPacoteDTOResponseList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(!isTesting()) logger.logUnusualBusinessActivity("Error getting tipoPacote list" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/All")
    public ResponseEntity deleteAll() {
        tipoPacoteService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{tipoPacoteId}")
    public ResponseEntity deleteById(@PathVariable Long tipoPacoteId) {
        if (tipoPacoteId < 0) return ResponseEntity.badRequest().build();
        tipoPacoteService.deleteById(tipoPacoteId);
        return ResponseEntity.ok().build();
    }
    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }

}
