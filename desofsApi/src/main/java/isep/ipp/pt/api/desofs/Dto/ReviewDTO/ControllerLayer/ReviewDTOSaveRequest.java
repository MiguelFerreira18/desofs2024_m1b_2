package isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer;

import jakarta.validation.constraints.*;

public class ReviewDTOSaveRequest {


    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Texto inválido")
    @Size(min = 1, max = 128, message = "Texto inválido")
    private final String reviewText;

    @Min(value = 0, message = "Rating inválido")
    @Max(value = 5, message = "Rating inválido")
    private final int rating;

    @NotNull
    private final String user;
    @NotNull
    @Positive
    private final Long pacote;

    public ReviewDTOSaveRequest(String reviewText, int rating, String user, Long pacote) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.user = user;
        this.pacote = pacote;
    }

    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }

    public String getUser() {
        return user;
    }

    public Long getPacote() {
        return pacote;
    }
}
