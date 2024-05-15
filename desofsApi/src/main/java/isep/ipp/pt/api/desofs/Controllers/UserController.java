package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Authentication.AuthenticationApi;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOResponse;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOPasswordChangeRequest;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ServiceLayer.UserDTOPasswordChange;
import isep.ipp.pt.api.desofs.Mapper.UserMapper.UserMapper;
import isep.ipp.pt.api.desofs.Model.UserModel.SignInRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import isep.ipp.pt.api.desofs.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationApi authenticationApi;

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserDTOResponse> getUserInfo(@PathVariable Long userId){
            if(userId < 0) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(userMapper.fromUserToUserDTOResponse(userService.getUserById(userId)));
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<Void> changePassword(@PathVariable Long userId, @RequestBody UserDTOPasswordChangeRequest request){
        if(userId < 0) return ResponseEntity.badRequest().build();
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
