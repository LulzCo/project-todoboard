package lulzco.todoboard.auth;

import lulzco.todoboard.user.User;
import lulzco.todoboard.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean login(LoginDto loginDto) {
        User user = userService.getUserByUserId(loginDto.getUserId());
        return user.getPassword().equals(loginDto.getPassword());
    }
}
