package isep.ipp.pt.api.desofs.Controllers;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOResponse;
import isep.ipp.pt.api.desofs.Mapper.UserMapper.UserMapper;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserDTOResponse> getUserInfo(@PathVariable Long userId){
            if(userId < 0) return ResponseEntity.badRequest().build();
            return ResponseEntity.ok(userMapper.fromUserToUserDTOResponse(userService.getUserById(userId)));
    }
}
