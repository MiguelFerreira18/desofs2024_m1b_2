package isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer;

import jakarta.validation.constraints.*;

public class ReviewDTOServicePatchRequest {

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
    @Positive
    private final Long user;
    @NotNull
    @Positive
    private final Long pacote;

    public ReviewDTOServicePatchRequest(Long reviewId,String reviewText, int rating, Long user, Long pacote) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.pacote = pacote;
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public Long getUser() {
        return user;
    }

    public Long getPacote() {
        return pacote;
    }

    public Long getReviewId() {
        return reviewId;
    }
}
