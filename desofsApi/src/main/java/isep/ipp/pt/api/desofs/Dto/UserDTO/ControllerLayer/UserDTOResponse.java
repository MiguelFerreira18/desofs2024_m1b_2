package isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer;

import isep.ipp.pt.api.desofs.Model.UserModel.Role;

import java.util.Set;

public record UserDTOResponse(Set<Role> authorities, String userId, String username, String fullName, String morada,
                              String nif) {
}
