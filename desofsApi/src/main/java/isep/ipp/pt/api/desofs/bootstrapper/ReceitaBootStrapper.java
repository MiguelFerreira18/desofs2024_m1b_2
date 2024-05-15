package isep.ipp.pt.api.desofs.bootstrapper;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Receita;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReceitaBootStrapper implements CommandLineRunner {
    @Autowired
    private ReceitaServiceRepo receitaRepo;

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaRepo;

    @Autowired
    private PacoteServiceRepo pacoteRepo;

    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    @Override
    public void run(String... args) throws Exception {
        if (receitaRepo.findbyName("receita1") == null) {
            TipoReceita tipoReceita = null;
            if (tipoReceitaRepo.findbyName("tipoReceita1") == null) {
                tipoReceita = new TipoReceita("tipoReceita1");
                tipoReceitaRepo.save(tipoReceita);
            }else{
                tipoReceita = tipoReceitaRepo.findbyName("tipoReceita1");
            }
            Pacote pacote = null;
            if (pacoteRepo.findbyName("pacote1") == null) {
                TipoPacote tipoPacote = null;
                if (tipoPacoteRepo.findbyName("tipoPacote1") == null) {
                    tipoPacote = new TipoPacote("tipoPacote1");
                    tipoPacoteRepo.save(tipoPacote);
                }else{
                    tipoPacote = tipoPacoteRepo.findbyName("tipoPacote1");
                }
                pacote = new Pacote("pacote1", 10.0, "pacotedescription", true ,tipoPacote);
                pacoteRepo.save(pacote);
            }else{
                pacote = pacoteRepo.findbyName("pacote1");
            }

            Receita receita = new Receita("./folder1/folder2/file-name.extension","receita1",pacote,tipoReceita);

            receitaRepo.save(receita);
        }

    }
}
