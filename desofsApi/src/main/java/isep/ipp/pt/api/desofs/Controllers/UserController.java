package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Authentication.AuthenticationApi;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOResponse;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOPasswordChangeRequest;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer.UserDTOPasswordChange;
import isep.ipp.pt.api.desofs.Mapper.UserMapper.UserMapper;
import isep.ipp.pt.api.desofs.Model.UserModel.SignInRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
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

    @Autowired
    private AuthenticationApi authenticationApi;

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserDTOResponse> getUserInfo(@PathVariable String userId){
            if(userId == null) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(userMapper.fromUserToUserDTOResponse(userService.getUserById(userId)));
    }
    @DeleteMapping("/delete/data")
    public ResponseEntity deleteUser(@RequestBody @Valid final SignInRequest request){
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (authentication == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        userService.deleteUser(request.username());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<Void> changePassword(@PathVariable String userId, @RequestBody UserDTOPasswordChangeRequest request){
        if(userId != null) return ResponseEntity.badRequest().build();
        UserDTOPasswordChange user = new UserDTOPasswordChange(UserDTOPasswordChangeRequest.getOldPassword(), UserDTOPasswordChangeRequest.getNewPassword());
//        UserDTOPasswordChange user = userMapper.fromUserDTOPassworChangeRequestToUserDTOPasswordChange(request);
        UserView u = authenticationApi.login(new SignInRequest(UserDTOPasswordChangeRequest.getUsername(), UserDTOPasswordChangeRequest.getOldPassword())).getBody();
        if (u == null) return ResponseEntity.badRequest().build();
        boolean changed = userService.changePassword(userId, user);
        if (changed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
