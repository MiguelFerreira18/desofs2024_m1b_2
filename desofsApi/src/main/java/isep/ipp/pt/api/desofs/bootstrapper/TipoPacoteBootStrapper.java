package isep.ipp.pt.api.desofs.bootstrapper;

import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TipoPacoteBootStrapper implements CommandLineRunner {

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    @Override
    public void run(String... args) throws Exception {
        if (tipoPacoteRepo.findbyName("Mediteraneo") == null) {
            TipoPacote tipoPacote = new TipoPacote("Mediteraneo");
            tipoPacoteRepo.save(tipoPacote);
        }

    }
}
