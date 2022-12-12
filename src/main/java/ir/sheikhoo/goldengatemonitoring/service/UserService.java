package ir.sheikhoo.goldengatemonitoring.service;



import ir.sheikhoo.goldengatemonitoring.model.User;
import ir.sheikhoo.goldengatemonitoring.model.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
