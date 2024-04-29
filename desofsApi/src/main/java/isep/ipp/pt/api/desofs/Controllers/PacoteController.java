package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Service.PacoteService.PacoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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


    @PostMapping("/save")
    public ResponseEntity savePacote(@Valid @RequestBody Pacote pacote){
        return ResponseEntity.ok(pacoteService.save(pacote));
    }


}
