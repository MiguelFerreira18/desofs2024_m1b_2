package isep.ipp.pt.api.desofs.Mapper;

import isep.ipp.pt.api.desofs.Model.UserModel.User;
import isep.ipp.pt.api.desofs.Model.UserModel.UserView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);

}
