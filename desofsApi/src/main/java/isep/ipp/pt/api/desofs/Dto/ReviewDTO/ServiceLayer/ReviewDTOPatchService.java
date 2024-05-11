package isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer;

import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import jakarta.validation.constraints.*;

public class ReviewDTOPatchService {


    @NotNull
    @Min(value = 0, message = "Id inválido")
    private final Long reviewId;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Texto inválido")
    @Size(min = 1, max = 128, message = "Texto inválido")
    private final String reviewText;

    @Min(value = 0, message = "Rating inválido")
    @Max(value = 5, message = "Rating inválido")
    private final int rating;

    @NotNull
    private final User user;
    @NotNull
    private final Pacote pacote;


    public ReviewDTOPatchService(Long reviewId, String reviewText, int rating, User user, Pacote pacote) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.pacote = pacote;
        this.reviewId = reviewId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public Pacote getPacote() {
        return pacote;
    }
}
