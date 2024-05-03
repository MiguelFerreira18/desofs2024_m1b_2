package isep.ipp.pt.api.desofs.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Entity
public class DetalhesEncomenda {
    @Id
    @GeneratedValue
    private Long detalhesEncomendaId;
    @Min(value = 1, message = "Número de refeições por semana inválido")
    @Max(value = 7, message = "Número de refeições por semana inválido")
    @Positive
    private int mealsPerWeek;

    @Min(value = 1, message = "Número de pessoas inválido")
    @Max(value = 5, message = "Número de pessoas inválido")
    @Positive
    private int numberOfPeople;
    @Min(value = 1, message = "Preço inválido")
    @Positive
    private double price;

    @ManyToOne
    private TipoPacote tipoPacote;
    @ManyToOne
    private ListaEncomenda listaEncomenda;

    public DetalhesEncomenda() {
    }

    public DetalhesEncomenda(Long detalhesEncomendaId, int mealsPerWeek, int numberOfPeople, double price, TipoPacote tipoPacote, ListaEncomenda listaEncomenda) {
        this.detalhesEncomendaId = detalhesEncomendaId;
        this.mealsPerWeek = mealsPerWeek;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.tipoPacote = tipoPacote;
        this.listaEncomenda = listaEncomenda;
    }

    public DetalhesEncomenda(int mealsPerWeek, int numberOfPeople, double price, TipoPacote tipoPacote, ListaEncomenda listaEncomenda) {
        this.mealsPerWeek = mealsPerWeek;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.tipoPacote = tipoPacote;
        this.listaEncomenda = listaEncomenda;
    }

    public Long getDetalhesEncomendaId() {
        return detalhesEncomendaId;
    }

    public void setDetalhesEncomendaId(Long detalhesEncomendaId) {
        this.detalhesEncomendaId = detalhesEncomendaId;
    }

    public int getMealsPerWeek() {
        return mealsPerWeek;
    }

    public void setMealsPerWeek(int mealsPerWeek) {
        this.mealsPerWeek = mealsPerWeek;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TipoPacote getTipoPacote() {
        return tipoPacote;
    }

    public void setTipoPacote(TipoPacote tipoPacote) {
        this.tipoPacote = tipoPacote;
    }

    public ListaEncomenda getListaEncomenda() {
        return listaEncomenda;
    }

    public void setListaEncomenda(ListaEncomenda listaEncomenda) {
        this.listaEncomenda = listaEncomenda;
    }
}
