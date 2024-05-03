package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Review;
import isep.ipp.pt.api.desofs.Repository.Interface.ReviewServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class ReviewServiceImpl implements ReviewServiceRepo {

    @Autowired
    private ReviewRepo reviewRepo;
    @Override
    public Review save(Review review) {
       return reviewRepo.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepo.deleteById(reviewId);
    }

    @Override
    public List<Review> getReviews() {
        List<Review> reviewList = new java.util.LinkedList<>();
        for (Review review : reviewRepo.findAll())
            reviewList.add(review);
        return reviewList;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepo.findById(reviewId).orElse(null);
    }

    @Override
    public List<Review> getReviewsByPacoteId(Long pacoteId) {
        return new LinkedList<>(reviewRepo.getReviewsByPacoteId(pacoteId));
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId) {
        return new LinkedList<>(reviewRepo.getReviewsByUserId(userId));
    }
}
