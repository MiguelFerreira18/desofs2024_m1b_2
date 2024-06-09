package isep.ipp.pt.api.desofs.Service.ReviewService;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceSaveRequest;
import isep.ipp.pt.api.desofs.Model.Review;

import java.util.List;

public interface ReviewService {

    public ReviewDTOServiceResponse addReview(ReviewDTOServiceSaveRequest review);
    public ReviewDTOServiceResponse updateReview(ReviewDTOServicePatchRequest review);
    public void deleteReview(Long reviewId);
    public List<ReviewDTOServiceResponse> getReviews();
    public ReviewDTOServiceResponse getReviewById(Long reviewId);
    public List<ReviewDTOServiceResponse> getReviewsByPacoteId(Long pacoteId);
    public List<ReviewDTOServiceResponse> getReviewsByUserId(String userId);
    public void deleteAll();

}
