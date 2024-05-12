package isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ControllerLayer;

import com.fasterxml.jackson.annotation.JsonFormat;
import isep.ipp.pt.api.desofs.Model.Estado;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class EncomendaDTOResponse {

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
    @NotNull
    private Pacote pacote;
    @NotNull
    private User user;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Estado da encomenda inválido")
    private Estado estado;

    public EncomendaDTOResponse(Long encomendaId, int mealsPerWeek, int numberOfPeople, double price, LocalDateTime dataEncomenda, Pacote pacote , Estado estado, User user) {
        this.mealsPerWeek = mealsPerWeek;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.dataEncomenda = dataEncomenda;
        this.pacote = pacote;
        this.estado = estado;
        this.encomendaId = encomendaId;
        this.user = user;
    }

    public int getMealsPerWeek() {
        return mealsPerWeek;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDataEncomenda() {
        return dataEncomenda;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public Estado getEstado() {
        return estado;
    }

    public Long getEncomendaId() {
        return encomendaId;
    }

    public User getUser() {
        return user;
    }
}
