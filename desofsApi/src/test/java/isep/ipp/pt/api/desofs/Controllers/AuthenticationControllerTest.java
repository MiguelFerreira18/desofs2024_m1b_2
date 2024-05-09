package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Authentication.AuthenticationApi;
import isep.ipp.pt.api.desofs.Model.ListaEncomenda;
import isep.ipp.pt.api.desofs.Model.UserModel.*;
import isep.ipp.pt.api.desofs.Service.UserService.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthenticationControllerTest {

    @Autowired
    private AuthenticationApi authenticationApi;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        Set<Role> authorities = new HashSet<>();
        User user = new User(1L, "username1@mail.com", encoder.encode("password1"), "fullName1", authorities, "nif1", "morada1", null);
        user.addAuthority(new Role(Role.Admin));
        userService.saveUser(user);

        User user2 = new User(2L, "username2@mail.com", encoder.encode("password2"), "fullName2", authorities, "nif2", "morada2", null);
        user2.addAuthority(new Role(Role.User));
        userService.saveUser(user2);
        }

    @AfterEach
    public void tearDown() {
        userService.deleteAll();
    }

    @Test
    @Transactional
    public void testLogin_ValidRequest() {
        SignInRequest signInRequest = new SignInRequest("username1@mail.com", "password1");

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
        SignUpRequest signUpRequest = new SignUpRequest("validUsername@mail.com", "validPassword", "validFullName", "213123123", "rua das ruas");

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