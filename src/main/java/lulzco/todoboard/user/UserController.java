package lulzco.todoboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            userService.create(user);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입이 완료되었습니다.");
        } catch (DuplicateIdException e) {
            return ResponseEntity.status(HttpStatus.OK).body("중복된 아이디가 존재합니다.");
        }

    }
}
