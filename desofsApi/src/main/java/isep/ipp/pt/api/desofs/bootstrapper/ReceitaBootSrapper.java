package isep.ipp.pt.api.desofs.bootstrapper;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Receita;
import isep.ipp.pt.api.desofs.Model.TipoReceita;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReceitaServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoReceitaServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReceitaBootSrapper implements CommandLineRunner {

    @Autowired
    private ReceitaServiceRepo receitaServiceRepo;

    @Autowired
    private TipoReceitaServiceRepo tipoReceitaServiceRepo;

    @Autowired
    private PacoteServiceRepo pacoteServiceRepo;

    @Override
    public void run(String... args) throws Exception {
//        if (receitaServiceRepo.findbyId(1L) == null) {
//            TipoReceita tipoReceita = null;
//
//            if (tipoReceitaServiceRepo.findbyName("Prato") == null) {
//                tipoReceita = new TipoReceita("Prato");
//                tipoReceitaServiceRepo.save(tipoReceita);
//            } else {
//                tipoReceita = tipoReceitaServiceRepo.findbyName("Prato");
//            }
//
//            Receita receita = new Receita(1L, "./folder/subfolder/file.extension", "nome", null, tipoReceita);
//            receitaServiceRepo.save(receita);
//
//        }
//
//        if (receitaServiceRepo.findbyId(2L) == null) {
//            TipoReceita tipoReceita = null;
//
//            if (tipoReceitaServiceRepo.findbyName("Sobremesa") == null) {
//                tipoReceita = new TipoReceita("Sobremesa");
//                tipoReceitaServiceRepo.save(tipoReceita);
//            } else {
//                tipoReceita = tipoReceitaServiceRepo.findbyName("Sobremesa");
//            }
//            Receita receita = new Receita(2L, "./folder/subfolder/file.extension", "nome", null, tipoReceita);
//            receitaServiceRepo.save(receita);
//        }
    }
}
