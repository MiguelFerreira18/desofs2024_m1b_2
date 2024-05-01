package isep.ipp.pt.api.desofs.Model.UserModel;

import lombok.Data;

@Data
public class UserView {
    Long userId;

    String username;

    String fullName;

    public UserView(Long userId, String username, String fullName) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
    }
}
