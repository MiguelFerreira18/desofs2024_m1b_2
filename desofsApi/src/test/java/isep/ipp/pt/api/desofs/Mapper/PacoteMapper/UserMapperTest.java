package isep.ipp.pt.api.desofs.Mapper.PacoteMapper;

import isep.ipp.pt.api.desofs.Mapper.UserMapper.UserMapper;
import isep.ipp.pt.api.desofs.Model.UserModel.Role;
import isep.ipp.pt.api.desofs.Model.UserModel.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void fromUserToUserDTOResponse() {
    User user = new User("username1", "password1", "fullname1", "nif123", "morada", new LinkedList<>());
    user.addAuthority(new Role("Admin"));
    user.addAuthority(new Role("User"));

    assertEquals(userMapper.fromUserToUserDTOResponse(user).getUserId(), user.getUserId());
    assertEquals(userMapper.fromUserToUserDTOResponse(user).getAuthorities(), user.getAuthorities());
    }
}
