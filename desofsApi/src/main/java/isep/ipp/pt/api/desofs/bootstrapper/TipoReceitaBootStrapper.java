package isep.ipp.pt.api.desofs.bootstrapper;


import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TipoReceitaBootStrapper implements CommandLineRunner {

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaRepo;

    @Override
    public void run(String... args) throws Exception {
        if (tipoReceitaRepo.findbyName("tipoReceita1") == null) {
            TipoReceita tipoReceita = new TipoReceita("tipoReceita1");
            tipoReceitaRepo.save(tipoReceita);
        }

        if (tipoReceitaRepo.findbyName("tipoReceita2") == null) {
            TipoReceita tipoReceita = new TipoReceita("tipoReceita2");
            tipoReceitaRepo.save(tipoReceita);
        }
    }
}
