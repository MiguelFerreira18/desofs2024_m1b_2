package isep.ipp.pt.api.desofs.Service.ReviewService;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.*;
import isep.ipp.pt.api.desofs.Mapper.ReviewMapper.ReviewMapper;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Review;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.ReviewServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {
    @Autowired
    private ReviewServiceRepo reviewServiceRepo;
    @Autowired
    private UserServiceRepo userServiceRepo;
    @Autowired
    private PacoteServiceRepo pacoteServiceRepo;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private PersonalValidation validation;


    @Override
    public ReviewDTOServiceResponse addReview(ReviewDTOServiceSaveRequest review) {
        User user = userServiceRepo.getUserById(review.getUser());
        Pacote pacote = pacoteServiceRepo.findbyId(review.getPacote());
        ReviewDTOSaveService reviewDTOSaveService = new ReviewDTOSaveService(review.getReviewText(), review.getRating(), user, pacote);
        if (!validation.validate(reviewDTOSaveService)) {
            return null;
        }
        return reviewMapper.toReviewDTOServiceResponseFromReview(reviewServiceRepo.save(reviewMapper.toReviewFromReviewSaveDtoService(reviewDTOSaveService)));
    }

    @Override
    public ReviewDTOServiceResponse updateReview(ReviewDTOServicePatchRequest review) {
        User user = userServiceRepo.getUserById(review.getUser());
        Pacote pacote = pacoteServiceRepo.findbyId(review.getPacote());
        ReviewDTOPatchService reviewDTOPatchService = new ReviewDTOPatchService(review.getReviewId(), review.getReviewText(), review.getRating(), user, pacote);
        if (!validation.validate(reviewDTOPatchService)) {
            return null;
        }

        return reviewMapper.toReviewDTOServiceResponseFromReview(reviewServiceRepo.save(reviewMapper.toReviewFromReviewPatchDtoService(reviewDTOPatchService)));
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewServiceRepo.deleteReview(reviewId);
    }

    @Override
    public List<ReviewDTOServiceResponse> getReviews() {
        return reviewMapper.toReviewDTOServiceResponseListFromReviewList(reviewServiceRepo.getReviews());
    }

    @Override
    public ReviewDTOServiceResponse getReviewById(Long reviewId) {
        return reviewMapper.toReviewDTOServiceResponseFromReview(reviewServiceRepo.getReviewById(reviewId));
    }

    @Override
    public List<ReviewDTOServiceResponse> getReviewsByPacoteId(Long pacoteId) {
        return reviewMapper.toReviewDTOServiceResponseListFromReviewList(reviewServiceRepo.getReviewsByPacoteId(pacoteId));
    }

    @Override
    public List<ReviewDTOServiceResponse> getReviewsByUserId(Long userId) {
        return reviewMapper.toReviewDTOServiceResponseListFromReviewList(reviewServiceRepo.getReviewsByUserId(userId));
    }

    @Override
    public void deleteAll() {
        reviewServiceRepo.deleteAll();
    }
}
