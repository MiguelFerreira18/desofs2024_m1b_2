package isep.ipp.pt.api.desofs.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
public class Encomenda {
    @Id
    @GeneratedValue
    private Long encomendaId;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataEncomenda;
    @ManyToOne
    @NotNull(message = "Pacote inválido")
    private Pacote pacote;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @NotNull(message = "User inválido")
    private User user;

    public Encomenda() {
    }

    public Encomenda(Long encomendaId, int mealsPerWeek, int numberOfPeople, double price, Pacote pacote, LocalDateTime dataEncomenda, Estado estado, User user) {
        this.encomendaId = encomendaId;
        this.mealsPerWeek = mealsPerWeek;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.pacote = pacote;
        this.dataEncomenda = dataEncomenda;
        this.estado = estado;
        this.user = user;
    }

    public Encomenda(int mealsPerWeek, int numberOfPeople, double price, Pacote pacote, LocalDateTime dataEncomenda, Estado estado, User user) {
        this.mealsPerWeek = mealsPerWeek;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.pacote = pacote;
        this.dataEncomenda = dataEncomenda;
        this.estado = estado;
        this.user = user;
    }

    public Long getEncomendaId() {
        return encomendaId;
    }

    public void setEncomendaId(Long encomendaId) {
        this.encomendaId = encomendaId;
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

    public LocalDateTime getDataEncomenda() {
        return dataEncomenda;
    }

    public void setDataEncomenda(LocalDateTime dataEncomenda) {
        this.dataEncomenda = dataEncomenda;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
