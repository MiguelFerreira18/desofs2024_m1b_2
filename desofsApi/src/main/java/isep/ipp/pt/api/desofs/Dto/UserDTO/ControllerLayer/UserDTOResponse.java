package isep.ipp.pt.api.desofs.Dto.UserDTO.ControllerLayer;

import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;

public class UserDTOResponse {

    @ElementCollection
    private Set<Role> authorities;

    @Id
    private Long userId;

    public UserDTOResponse(Set<Role> authorities, Long userId) {
        this.authorities = authorities;
        this.userId = userId;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public Long getUserId() {
        return userId;
    }
}
