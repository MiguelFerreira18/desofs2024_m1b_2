package isep.ipp.pt.api.desofs.Mapper.UserMapper;

import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOResponse;
import isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer.UserDTOSignup;
import isep.ipp.pt.api.desofs.Model.UserModel.SignUpRequest;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDTOResponse fromUserToUserDTOResponse(User user);

    UserDTOSignup fromSignUpRequestToUserDTOSignup(SignUpRequest signUpRequest);

}
