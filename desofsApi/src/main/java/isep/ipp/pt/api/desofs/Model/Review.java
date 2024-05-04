package isep.ipp.pt.api.desofs.Model;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long reviewId;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Texto inválido")
    private String reviewText;

    @Min(value = 0, message = "Rating inválido")
    @Max(value = 5, message = "Rating inválido")
    @Positive
    private int rating;

    @ManyToOne
    private User user;

    @ManyToOne
    private Pacote pacote;

    public Review() {
    }

    public Review(Long reviewId, String reviewText, int rating, User user, Pacote pacote) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.pacote = pacote;
    }

    public Review(String reviewText, int rating, User user, Pacote pacote) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.pacote = pacote;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }
}
