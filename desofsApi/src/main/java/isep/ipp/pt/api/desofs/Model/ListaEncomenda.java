package isep.ipp.pt.api.desofs.Model;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;

@Entity
public class ListaEncomenda {

    @Id
    private Long listaEncomendaId;
    private LocalDateTime dataEncomenda;

    @ManyToOne
    private Pacote pacote;

    protected ListaEncomenda() {
    }


}
