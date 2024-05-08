package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOPatchRequest;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOResponse;
import isep.ipp.pt.api.desofs.Dto.ReviewDTO.ControllerLayer.ReviewDTOSaveRequest;
import isep.ipp.pt.api.desofs.Model.Pacote;
import isep.ipp.pt.api.desofs.Model.TipoPacote;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Repository.Interface.PacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.TipoPacoteServiceRepo;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import isep.ipp.pt.api.desofs.Service.ReviewService.ReviewService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewControllerTest {
    //TO Be TESTED
    @Autowired
    private ReviewController reviewController;

    // Dependencies
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserServiceRepo userRepo;
    @Autowired
    private PacoteServiceRepo pacoteRepo;
    @Autowired
    private TipoPacoteServiceRepo tipoPacoteRepo;

    private int randomAdmingNum = (int) (Math.random() * 90000000) + 10000000;

    @BeforeEach
    void setUp() {
        reviewService.deleteAll();
        int randomNum = (int) (Math.random() * 900000000) + 100000000;
        TipoPacote tp1 = new TipoPacote("TugaTube");
        TipoPacote tp = tipoPacoteRepo.save(tp1);
        User admin1 = new User(1L, "admin" + randomAdmingNum + "@mail.com", "adminpw1", "miguel", "" + randomNum, "RUA cena");
        admin1.addAuthority(new Role(Role.Admin));
        userRepo.saveUser(admin1);
        Pacote pacote = new Pacote(1L,"Pacote1", 10.0, "Pacote1 Description", true, tp);
        pacoteRepo.save(pacote);

    }


    @AfterEach
    public void tearDown() {
        reviewService.deleteAll();
        pacoteRepo.deleteAll();
        userRepo.deleteAll();
        tipoPacoteRepo.deleteAll();
    }

    @Test
    @Order(1)
    public void testSaveReview_ValidRequest() {
        Long pacoteId = pacoteRepo.findbyName("Pacote1").getPacoteId();
        Long userId = userRepo.findByUserByEmail("admin" + randomAdmingNum + "@mail.com").getUserId();
        ReviewDTOSaveRequest reviewDTOSaveRequest = new ReviewDTOSaveRequest("Review1", 5, userId , pacoteId);

        ResponseEntity<ReviewDTOResponse> response = reviewController.saveReview(reviewDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "Review1, 5",
            "Review2, 4",
            "Review3, 3",
            "Review4, 2",
            "Review5, 1",
    })
    @Order(2)
    public void testSaveReview_ValidRequest_Parameterized(String nome, int rating) {
        Long pacoteId = pacoteRepo.findbyName("Pacote1").getPacoteId();
        Long userId = userRepo.findByUserByEmail("admin" + randomAdmingNum + "@mail.com").getUserId();
        ReviewDTOSaveRequest reviewDTOSaveRequest = new ReviewDTOSaveRequest(nome, rating, userId, pacoteId);

        ResponseEntity<ReviewDTOResponse> response = reviewController.saveReview(reviewDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    public void testSaveReview_InvalidRequest() {
        ReviewDTOSaveRequest reviewDTOSaveRequest = new ReviewDTOSaveRequest("a", -1, 1L, 1L);

        ResponseEntity<ReviewDTOResponse> response = reviewController.saveReview(reviewDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "a, -1, 1, 1",
            "b, -2, 1, 1",
            "c, -3, 1, 1",
            "d, -4, 1, 1",
            "e, -5, 1, 1",
    })
    @Order(4)
    public void testSaveReview_InvalidRequest_Parameterized(String nome, int rating, long userId, long pacoteId) {
        ReviewDTOSaveRequest reviewDTOSaveRequest = new ReviewDTOSaveRequest(nome, rating, userId, pacoteId);

        ResponseEntity<ReviewDTOResponse> response = reviewController.saveReview(reviewDTOSaveRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @Order(5)
    public void testUpdateReview_ValidRequest() {
        Long pacoteId = pacoteRepo.findbyName("Pacote1").getPacoteId();
        Long userId = userRepo.findByUserByEmail("admin" + randomAdmingNum + "@mail.com").getUserId();
        ReviewDTOPatchRequest reviewDTOSaveRequest = new ReviewDTOPatchRequest(1L,"Review1", 5, userId ,pacoteId);

        ResponseEntity<ReviewDTOResponse> response = reviewController.updateReview(reviewDTOSaveRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(6)
    public void testGetReview_InvalidRequest() {
        ResponseEntity<ReviewDTOResponse> response = reviewController.getReview(-120L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5",
    })
    @Order(7)
    public void testGetReview_InvalidRequest_Parameterized(Long id) {
        ResponseEntity<ReviewDTOResponse> response = reviewController.getReview(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5",
    })
    @Order(8)
    public void testGetReviewByUserId_InvalidRequest_Parameterized(Long id) {
        ResponseEntity<List<ReviewDTOResponse>> response = reviewController.getReviewsByUserId(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
    @ParameterizedTest
    @CsvSource({
            "-1",
            "-2",
            "-3",
            "-4",
            "-5",
    })
    @Order(7)
    public void testGetReviewByPacoteId_InvalidRequest_Parameterized(Long id) {
        ResponseEntity<List<ReviewDTOResponse>> response = reviewController.getReviewsByPacoteId(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

}