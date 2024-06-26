package isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer;

import jakarta.validation.constraints.*;

public class ReviewDTOServiceSaveRequest {


    @NotBlank
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Texto inválido")
    @Size(min = 1, max = 128, message = "Texto inválido")
    private final String reviewText;

    @Min(value = 0, message = "Rating inválido")
    @Max(value = 5, message = "Rating inválido")
    private final int rating;

    @NotNull
    @Size(min = 36, max = 36, message = "User inválido")
    private final String user;
    @NotNull
    @Min(value = 0, message = "Pacote inválido")
    private final Long pacote;

    public ReviewDTOServiceSaveRequest(String reviewText, int rating, String user, Long pacote) {
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
