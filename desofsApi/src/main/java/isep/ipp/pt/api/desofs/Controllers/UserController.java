package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOResponse;
import isep.ipp.pt.api.desofs.Mapper.UserMapper.UserMapper;
import isep.ipp.pt.api.desofs.Model.UserModel.SignInRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Service.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserDTOResponse> getUserInfo(@PathVariable Long userId){
            if(userId < 0) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(userMapper.fromUserToUserDTOResponse(userService.getUserById(userId)));
    }
    @DeleteMapping("/delete/data")
    public ResponseEntity deleteUser(@RequestBody @Valid final SignInRequest request){
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (authentication == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        userService.deleteUser(request.username());
        return ResponseEntity.ok().build();
    }
}
