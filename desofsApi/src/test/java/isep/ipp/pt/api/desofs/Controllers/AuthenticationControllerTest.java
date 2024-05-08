package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Authentication.AuthenticationApi;
import isep.ipp.pt.api.desofs.Model.UserModel.SignInRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.SignUpRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticationControllerTest {

    @Autowired
    private AuthenticationApi authenticationApi;

    @Test
    @Transactional
    public void testLogin_ValidRequest() {
        SignInRequest signInRequest = new SignInRequest("admin1@mail.com", "adminpw1");

        ResponseEntity<UserView> response = authenticationApi.login(signInRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Transactional
    public void testLogin_InvalidRequest() {
        SignInRequest signInRequest = new SignInRequest("admin1@mail.com", "oweuroweuir");

        ResponseEntity<UserView> response = authenticationApi.login(signInRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @Transactional
    public void testSignup_ValidRequest() {
        SignUpRequest signUpRequest = new SignUpRequest("validUsername", "validPassword", "validFullName", "213123123", "rua das ruas");

        ResponseEntity<UserView> response = authenticationApi.signup(signUpRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Transactional
    public void testSignup_InvalidRequest() {
        SignUpRequest signUpRequest = new SignUpRequest("", "validPassword", "validFullName", "213123123", "rua das ruas");

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationApi.signup(signUpRequest);
        });
    }
}