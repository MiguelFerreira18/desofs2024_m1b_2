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

    public ListaEncomenda() {
    }

    public ListaEncomenda(Long listaEncomendaId, LocalDateTime dataEncomenda, Pacote pacote) {
        this.listaEncomendaId = listaEncomendaId;
        this.dataEncomenda = dataEncomenda;
        this.pacote = pacote;
    }

    public ListaEncomenda(LocalDateTime dataEncomenda, Pacote pacote) {
        this.dataEncomenda = dataEncomenda;
        this.pacote = pacote;
    }
    public ListaEncomenda(Pacote pacote) {
        this.dataEncomenda = LocalDateTime.now();
        this.pacote = pacote;
    }



}
