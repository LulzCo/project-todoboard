package lulzco.todoboard.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;

    @Autowired
    UserServiceTest(UserService userService) {
        this.userService = userService;
    }


    @Test
    @DisplayName("사용자 생성 및 조회")
    void create() throws DuplicateIdException {
        String name = "홍길동";
        String userId = "ghdrlfehd1234";
        String password = "rlfehddl1234";

        User user = new User();
        user.setName(name);
        user.setUserId(userId);
        user.setPassword(password);

        userService.create(user);

        User createdUser = userService.getUserById(user.getId());

        Assertions.assertThat(createdUser.getName()).isEqualTo(name);
        Assertions.assertThat(createdUser.getUserId()).isEqualTo(userId);
        Assertions.assertThat(createdUser.getPassword()).isEqualTo(password);
    }
}