package isep.ipp.pt.api.desofs.Service.ReviewService;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServicePatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ServiceLayer.ReviewDTOServiceSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.Review;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceImpTest {
    //To be tested
    @Autowired
    private ReviewService reviewService;

    //Dependencies
    @Autowired
    private ReviewServiceRepo reviewRepo;
    @Autowired
    private UserServiceRepo userRepo;
    @Autowired
    private PacoteServiceRepo pacoteRepo;
    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;
    @Autowired
    private ReceitaServiceRepo receitaRepo;

    private Validator validator;

    private int randomAdminNum = (int) (Math.random() * 90000000) + 10000000;

    @BeforeEach
    void setUp() {
        receitaRepo.deleteAll();
        reviewRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();

        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        TipoPacote tp1 = new TipoPacote("TugaTube");
        TipoPacote tp = tipoPacoteRepo.save(tp1);
        User admin1 = new User(1L, "admin" + randomAdminNum + "@mail.com", "adminpw1", "miguel", "" + randomNum, "RUA cena");
        admin1.addAuthority(new Role(Role.Admin));
        User user = userRepo.saveUser(admin1);
        Pacote pacotec = new Pacote(1L,"Pacote1", 10.0, "Pacote1 Description", true, tp);
        Pacote pacote = pacoteRepo.save(pacotec);

        Review review1 = new Review(1L, "MyReview1", 2, user, pacote);
        Review review2 = new Review(2L, "MyReview2", 3, user, pacote);
        Review review3 = new Review(3L, "MyReview3", 1, user, pacote);
        Review review4 = new Review(4L, "MyReview4", 4, user, pacote);
        Review review5 = new Review(5L, "MyReview5", 5, user, pacote);
        reviewRepo.save(review1);
        reviewRepo.save(review2);
        reviewRepo.save(review3);
        reviewRepo.save(review4);
        reviewRepo.save(review5);


        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterEach
    void tearDown() {
        receitaRepo.deleteAll();
        reviewRepo.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
    }

    @ParameterizedTest
    @CsvSource({
            "MyReview6, 5",
            "MyReview7, 4",
            "MyReview8, 3",
            "MyReview9, 2",
            "MyReview10, 1",
    })
    void testSaveReviewSuccess(String review, int rating) {
        reviewRepo.deleteAll();

        User user = userRepo.findByUserByEmail("admin" + randomAdminNum + "@mail.com");
        Pacote pacote = pacoteRepo.findbyName("Pacote1");


        ReviewDTOServiceSaveRequest reviewDTOServiceRequest = new ReviewDTOServiceSaveRequest(review, rating, user.getUserId(), pacote.getPacoteId());

        Set<ConstraintViolation<ReviewDTOServiceSaveRequest>> violations = validator.validate(reviewDTOServiceRequest);

        ReviewDTOServiceResponse reviewDTOServiceResponse = reviewService.addReview(reviewDTOServiceRequest);
        assertNotNull(reviewDTOServiceResponse);
        assertEquals(reviewDTOServiceResponse.getRating(), reviewDTOServiceRequest.getRating());
        assertEquals(reviewDTOServiceResponse.getReviewText(), reviewDTOServiceRequest.getReviewText());
        assertTrue(violations.isEmpty());

        Review r = reviewRepo.getReviewById(reviewDTOServiceResponse.getReviewId());
        assertNotNull(r);
    }

    @ParameterizedTest
    @CsvSource({
            "MyReview6hiou fwe8y9 fwef ewy8n v34y8ntv348yn923gi 8ny t4n7 t34h ut4 wn890  rt234ugi frw3 80ny9 efw4 giu efw H8 'FW  [ 2[03489T208934T0892T, 5, 1, 1",
            "DROP TABLE receita --, 4, 1, 1",
            "MyReview8, 10, 1, 1",
            "<script>alert('XSS')</script>, 2, 1, 1",
            "MyReview11, -1, 1, 1",
            "MyReview12, -1, 1, 1",
            "MyReview13, 1, -1, 1",
            "MyReview1<>as C SDC  129RE40112 R491 9010 U138 R5892359235Y2385ASDJKNASJKDN JIKH79EY- GT=BVFVMDV;L'SD WD 0-=Q0 PF0 O30TRGI8H8 9NU3RNB VB, 1, 1, 1",
            "MyReview15, 30, -1, 1",
            "MyReview16, 20, -1, -1",
            "MyReview17, 99999, 1, 1",
    })
    void testSaveReviewFail(String review, int rating, Long user, Long pacote) {
        ReviewDTOServiceSaveRequest reviewDTOServiceRequest = new ReviewDTOServiceSaveRequest(review, rating, user, pacote);
        Set<ConstraintViolation<ReviewDTOServiceSaveRequest>> violations = validator.validate(reviewDTOServiceRequest);
        ReviewDTOServiceResponse reviewDTOServiceResponse = null;
        try {
            reviewDTOServiceResponse = reviewService.addReview(reviewDTOServiceRequest);
        } catch (Exception e) {
            assertNull(reviewDTOServiceResponse);
        }
        assertFalse(violations.isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
            "1,NewReviewTex-=p=p-o4 t90ij t3490 udfgr ak [opt,1,1,1",
            "2,NewReviewText90io=54690-23450-9345690-()_+()_(*)__()*_-78940-5 -0===-7348 ]][,1,1,1",
            "3,<script>alert('XSS')</script>,4,1,1",
            "4,][[]][][]][]1[4124][........12@@##$%#%$%*&^)(!@#@&^&)*_+P{<>><?<<,2,1,1",
            "5,NewReviewText,2,-1,1",
            "5,NewReviewText,2,1,-1",
            "5,NewReviewText,4893589,1,-1",
            "5,NewReviewText,348832,1,-1",
            "5,NewReviewText,289045689,1,-1",
            "5,NewReviewText,-238,1,-1",
            "5,NewReviewText,-238,1,-1",
            "-5141,NewReviewText,3,1,1",
            "-5,NewReviewText,3,1,1",
    })
    void testUpdateReviewFail(Long reviewId, String review, int rating, Long user, Long pacote) {
        ReviewDTOServicePatchRequest reviewDTOServiceRequest = new ReviewDTOServicePatchRequest(reviewId, review, rating, user, pacote);
        Set<ConstraintViolation<ReviewDTOServicePatchRequest>> violations = validator.validate(reviewDTOServiceRequest);
        ReviewDTOServiceResponse reviewDTOServiceResponse = null;
        try {
            reviewDTOServiceResponse = reviewService.updateReview(reviewDTOServiceRequest);
        } catch (Exception e) {
            assertNull(reviewDTOServiceResponse);
        }
    }


    @ParameterizedTest
    @CsvSource({
            "-5",
            "-20124",
            "-234590",
            "-49045329",
            "-909999999",
    })
    void testFindByIdFail(Long id) {
        ReviewDTOServiceResponse reviewDTOServiceResponse = reviewService.getReviewById(id);
        assertNull(reviewDTOServiceResponse);

    }

    @ParameterizedTest
    @CsvSource({
            "-5",
            "-20124",
            "-234590",
            "-49045329",
            "-909999999",
    })
    void testFindByUserIdFail(Long id) {
        List<ReviewDTOServiceResponse> reviewDTOServiceResponse = reviewService.getReviewsByUserId(id);
        assertTrue(reviewDTOServiceResponse.isEmpty());

    }

    @ParameterizedTest
    @CsvSource({
            "-5",
            "-20124",
            "-234590",
            "-49045329",
            "-909999999",
    })
    void testFindByPacoteIdFail(Long id) {
        List<ReviewDTOServiceResponse> reviewDTOServiceResponse = reviewService.getReviewsByPacoteId(id);
        assertTrue(reviewDTOServiceResponse.isEmpty());

    }

    @Test
    void testGetReviewsSuccess() {
        List<ReviewDTOServiceResponse> reviewDTOServiceResponse = reviewService.getReviews();
        assertNotNull(reviewDTOServiceResponse);
        assertEquals(reviewDTOServiceResponse.size(), 5);
    }


}