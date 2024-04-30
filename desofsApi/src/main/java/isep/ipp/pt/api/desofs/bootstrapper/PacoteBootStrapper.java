package isep.ipp.pt.api.desofs.bootstrapper;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PacoteBootStrapper implements CommandLineRunner {

    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("PacoteBootStrapper");
        if (pacoteRepo.findbyId(1L) == null) {
            TipoPacote tipoPacote = tipoPacoteRepo.findbyId(1L);
            Pacote pacote = new Pacote("pacote", 10.0, "pacotedescription", true ,tipoPacote);

            pacoteRepo.save(pacote);
        }

    }
}
