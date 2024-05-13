package isep.ipp.pt.api.desofs.bootstrapper;

import isep.ipp.pt.api.desofs.Model.DadosNutricionais;
import isep.ipp.pt.api.desofs.Repository.Interface.DadosNutricionaisServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DadosNutricionaisBootStrapper implements CommandLineRunner {

    @Autowired
    private DadosNutricionaisServiceRepo dadosNutricionaisServiceRepo;

    @Override
    public void run(String... args) throws Exception {
//        if (dadosNutricionaisServiceRepo.getDadosNutricionaisByReceitaId(1L) == null) {
//            DadosNutricionais dados = new DadosNutricionais(1L, 102L, "4 porções", "502Kcal", "1002g", "569g", "100g", "5g", "50g");
//            dadosNutricionaisServiceRepo.save(dados);
//        }
//        if (dadosNutricionaisServiceRepo.getDadosNutricionaisByReceitaId(2L) == null) {
//            DadosNutricionais dados = new DadosNutricionais(2L, 103L, "2 porções", "142Kcal", "702g", "439g", "16g", "35g", "57g");
//            dadosNutricionaisServiceRepo.save(dados);
//        }
    }
}
