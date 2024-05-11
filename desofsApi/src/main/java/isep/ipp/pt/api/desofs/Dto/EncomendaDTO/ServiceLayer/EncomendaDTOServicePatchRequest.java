package isep.ipp.pt.api.desofs.Dto.EncomendaDTO.ServiceLayer;

import com.fasterxml.jackson.annotation.JsonFormat;
import isep.ipp.pt.api.desofs.Model.Pacote;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public class EncomendaDTOServicePatchRequest {
    @NotNull
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
    private Long pacoteId;
    @NotNull
    private Long userId;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Estado da encomenda inválido")
    private String estado;

    public EncomendaDTOServicePatchRequest(Long encomendaId, int mealsPerWeek, int numberOfPeople, double price, LocalDateTime dataEncomenda, Long pacoteId, String estado, Long userId) {
        this.encomendaId = encomendaId;
        this.mealsPerWeek = mealsPerWeek;
        this.numberOfPeople = numberOfPeople;
        this.price = price;
        this.dataEncomenda = dataEncomenda;
        this.pacoteId = pacoteId;
        this.estado = estado;
        this.userId = userId;
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

    public Long getPacoteId() {
        return pacoteId;
    }

    public String getEstado() {
        return estado;
    }

    public Long getEncomendaId() {
        return encomendaId;
    }

    public Long getUserId() {
        return userId;
    }
}
