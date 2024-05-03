package isep.ipp.pt.api.desofs.Repository.Interface;

import isep.ipp.pt.api.desofs.Model.Review;

import java.util.List;

public interface ReviewServiceRepo {
    public Review addReview(Review review);
    public void  deleteReview(Long reviewId);
    public List<Review> getReviews();
    public Review getReviewById(Long reviewId);
    public List<Review> getReviewsByPacoteId(Long pacoteId);
    public List<Review> getReviewsByUserId(Long userId);
}
