package isep.ipp.pt.api.desofs.Mapper.ReviewMapper;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.*;
import isep.ipp.pt.api.desofs.Model.Review;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ReviewMapper {

    //Controller Layer
    ReviewDTOServiceSaveRequest toReviewDtoServiceSaveRequestFromReviewDtoSaveRequest(ReviewDTOSaveRequest review);
    ReviewDTOResponse fromReviewDTOServiceResponseToReviewDTOResponse(ReviewDTOServiceResponse reviewServiceResponse);
    ReviewDTOServicePatchRequest toReviewDTOServiceSaveRequestFromReviewDTOPatchRequest(ReviewDTOPatchRequest review);
    List<ReviewDTOResponse> fromReviewDTOServiceResponseListToReviewDTOResponseList(List<ReviewDTOServiceResponse> reviews);


    //Service Layer
    Review toReviewFromReviewSaveDtoService(ReviewDTOSaveService reviewDTOSaveService);
    ReviewDTOServiceResponse toReviewDTOServiceResponseFromReview(Review save);
    Review toReviewFromReviewPatchDtoService(ReviewDTOPatchService reviewDTOPatchService);
    List<ReviewDTOServiceResponse> toReviewDTOServiceResponseListFromReviewList(List<Review> reviews);

}
