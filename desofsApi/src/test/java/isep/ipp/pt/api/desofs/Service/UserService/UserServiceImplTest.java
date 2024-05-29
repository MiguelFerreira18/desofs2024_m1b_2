package isep.ipp.pt.api.desofs.Service.UserService;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOSignup;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import isep.ipp.pt.api.desofs.Repository.Interface.UserServiceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserServiceRepo userServiceRepo;

    @Mock
    BCryptPasswordEncoder encoder;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void valid_getUserById() {
        User user = new User("AA", "username", "password", "fullname", "nif", "morada");
        when(userServiceRepo.getUserById("AA")).thenReturn(user);

        User result = userService.getUserById("AA");

        assertNotNull(result);
        assertEquals("username", result.getUsername());
    }

    @Test
    public void invalid_getUserById() {
        when(userServiceRepo.getUserById("AA")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> userService.getUserById("AA"));
    }

    @Test
    public void valid_findByUsername() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userServiceRepo.findByUsername("username")).thenReturn(userDetails);

        UserDetails result = userService.findByUsername("username");

        assertNotNull(result);
    }

    @Test
    public void invalid_findByUsername() {
        when(userServiceRepo.findByUsername("username")).thenReturn(null);

        UserDetails result = userService.findByUsername("username");

        assertNull(result);
    }

    @Test
    public void valid_loadUserByUsername() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userServiceRepo.findByUsername("username")).thenReturn(userDetails);

        UserDetails result = userService.loadUserByUsername("username");

        assertNotNull(result);
    }

    @Test
    public void invalid_loadUserByUsername() {
        when(userServiceRepo.findByUsername("username")).thenReturn(null);

        UserDetails result = userService.loadUserByUsername("username");

        assertNull(result);
    }

    @Test
    public void invalid_validateUser() {
        User user = new User("AA", null, null, "fullname", "nif", "morada");

        User result = userService.validateUser(user);

        assertNull(result);
    }

    @Test
    public void valid_registerUser() {
        UserDTOSignup userDto = new UserDTOSignup("username", "password", "fullname", "nif", "morada");
        when(userServiceRepo.findByUsername("username")).thenReturn(null);
        when(encoder.encode("password")).thenReturn("encodedPassword");

        UserView result = userService.registerUser(userDto);

        assertNotNull(result);
        assertEquals("username", result.getUsername());
    }

    @Test
    public void invalid_registerUser() {
        UserDTOSignup userDto = new UserDTOSignup("", "", "fullname", "nif", "morada");

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userDto));
    }
}