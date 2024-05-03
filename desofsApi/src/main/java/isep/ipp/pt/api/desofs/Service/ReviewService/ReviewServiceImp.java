package isep.ipp.pt.api.desofs.Service.ReviewService;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceSaveRequest;
import isep.ipp.pt.api.desofs.Model.Review;
import isep.ipp.pt.api.desofs.Repository.Interface.ReviewServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService{
    @Autowired
    private ReviewServiceRepo reviewServiceRepo;


    @Override
    public ReviewDTOServiceResponse addReview(ReviewDTOServiceSaveRequest review) {
        return null;
    }

    @Override
    public ReviewDTOServiceResponse updateReview(ReviewDTOServicePatchRequest review) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }

    @Override
    public List<ReviewDTOServiceResponse> getReviews() {
        return List.of();
    }

    @Override
    public ReviewDTOServiceResponse getReviewById(Long reviewId) {
        return null;
    }

    @Override
    public List<ReviewDTOServiceResponse> getReviewsByPacoteId(Long pacoteId) {
        return List.of();
    }

    @Override
    public List<ReviewDTOServiceResponse> getReviewsByUserId(Long userId) {
        return List.of();
    }
}
