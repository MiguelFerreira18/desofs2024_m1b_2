package isep.ipp.pt.api.desofs.Repository.Implementation;

import isep.ipp.pt.api.desofs.Model.Review;
import isep.ipp.pt.api.desofs.Repository.Interface.ReviewServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Repo.ReviewRepo;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;

public class ReviewServiceImpl implements ReviewServiceRepo {

    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private LoggerStrategy logger;
    @Autowired
    private PasswordEncoder encoder;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;


    @Override
    public Review save(Review review) {
        if(!isTesting()) logger.log(review.copy(encoder).toString());
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
    public List<Review> getReviewsByUserId(String userId) {
        return new LinkedList<>(reviewRepo.getReviewsByUserId(userId));
    }

    @Override
    public void deleteAll() {
        reviewRepo.deleteAll();
    }

    @Override
    public void deleteReviewsByUserName(String username) {
        if(!isTesting()) reviewRepo.getReviewsByUserName(username).forEach(review -> logger.log(review.copy(encoder).toString()));
        reviewRepo.deleteReviewsByUserName(username);
    }

    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }
}
