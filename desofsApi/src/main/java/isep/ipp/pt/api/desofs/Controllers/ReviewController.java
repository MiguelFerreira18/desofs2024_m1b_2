package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOSaveRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOPatchService;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceSaveRequest;
import isep.ipp.pt.api.desofs.Mapper.ReviewMapper.ReviewMapper;
import isep.ipp.pt.api.desofs.Service.ReviewService.ReviewService;
import isep.ipp.pt.api.desofs.Utils.DatabaseLogger;
import isep.ipp.pt.api.desofs.Utils.LoggerStrategy;
import isep.ipp.pt.api.desofs.Utils.PersonalValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private PersonalValidation validation;
    @Autowired
    private LoggerStrategy logger;
    @Value("${app.logger.strategy}")
    private String loggerStrategy;

    @PostMapping("/save")
    public ResponseEntity<ReviewDTOResponse> saveReview(@Valid @RequestBody ReviewDTOSaveRequest review) {
        try {
            ReviewDTOServiceSaveRequest reviewRequestService = reviewMapper.toReviewDtoServiceSaveRequestFromReviewDtoSaveRequest(review);
            if (!validation.validate(reviewRequestService)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            ReviewDTOServiceResponse reviewServiceResponse = reviewService.addReview(reviewRequestService);
            ReviewDTOResponse reviewDTOResponse = reviewMapper.fromReviewDTOServiceResponseToReviewDTOResponse(reviewServiceResponse);
            return ResponseEntity.ok(reviewDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(!isTesting()) logger.logUnusualBusinessActivity("Error saving review" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ReviewDTOResponse> updateReview(@Valid @RequestBody ReviewDTOPatchRequest review) {
        try {
            ReviewDTOServicePatchRequest reviewRequestService = reviewMapper.toReviewDTOServiceSaveRequestFromReviewDTOPatchRequest(review);
            if (!validation.validate(reviewRequestService)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            ReviewDTOServiceResponse reviewServiceResponse = reviewService.updateReview(reviewRequestService);
            ReviewDTOResponse reviewDTOResponse = reviewMapper.fromReviewDTOServiceResponseToReviewDTOResponse(reviewServiceResponse);
            return ResponseEntity.ok(reviewDTOResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if(!isTesting()) logger.logUnusualBusinessActivity("Error updating review" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview( @PathVariable Long reviewId) {
        if (reviewId < 0) return ResponseEntity.badRequest().build();
        reviewService.deleteReview(reviewId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{reviewId}")
    public ResponseEntity<ReviewDTOResponse> getReview(@PathVariable Long reviewId) {
        if (reviewId < 0) return ResponseEntity.badRequest().build();
        ReviewDTOServiceResponse reviewServiceResponse = reviewService.getReviewById(reviewId);
        ReviewDTOResponse reviewDTOResponse = reviewMapper.fromReviewDTOServiceResponseToReviewDTOResponse(reviewServiceResponse);
        return ResponseEntity.ok(reviewDTOResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDTOResponse>> getReviews() {
        List<ReviewDTOResponse> reviewDTOResponse = reviewMapper.fromReviewDTOServiceResponseListToReviewDTOResponseList(reviewService.getReviews());
        return ResponseEntity.ok(reviewDTOResponse);
    }

    @GetMapping("/pacote/{pacoteId}")
    public ResponseEntity<List<ReviewDTOResponse>> getReviewsByPacoteId(@PathVariable Long pacoteId) {
        if (pacoteId < 0) return ResponseEntity.badRequest().build();
        List<ReviewDTOResponse> reviewDTOResponse = reviewMapper.fromReviewDTOServiceResponseListToReviewDTOResponseList(reviewService.getReviewsByPacoteId(pacoteId));
        return ResponseEntity.ok(reviewDTOResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDTOResponse>> getReviewsByUserId(@PathVariable String userId) {
        if (userId == null || isValidUUID(userId) == null ) return ResponseEntity.badRequest().build();
        List<ReviewDTOResponse> reviewDTOResponse = reviewMapper.fromReviewDTOServiceResponseListToReviewDTOResponseList(reviewService.getReviewsByUserId(userId));
        return ResponseEntity.ok(reviewDTOResponse);
    }

    private UUID isValidUUID(String userId) {
        try {
            return UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            if(!isTesting()) logger.logUnusualBusinessActivity("Error getting review by user id" + e.getMessage());
            return null;
        }
    }
    private boolean isTesting() {
        if (loggerStrategy.equals("test")) {
            return true;
        }
        return false;
    }

}
