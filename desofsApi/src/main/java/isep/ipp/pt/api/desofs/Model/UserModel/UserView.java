package isep.ipp.pt.api.desofs.Model.UserModel;

import lombok.Data;

import java.util.Set;

@Data
public class UserView {
    Long userId;

    String username;

    String fullName;

    Set<Role> authorities;

    public UserView(Long userId, String username, String fullName, Set<Role> authorities) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.authorities = authorities;
    }
}
