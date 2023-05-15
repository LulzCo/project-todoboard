package lulzco.todoboard.user;

import lulzco.todoboard.user.utils.EncryptionUtil;
import lulzco.todoboard.user.utils.SignupValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;
    private final EncryptionUtil encryptionUtil;
    private final SignupValidator signupValidator;

    @Autowired
    UserServiceTest(UserService userService, EncryptionUtil encryptionUtil, SignupValidator signupValidator) {
        this.userService = userService;
        this.encryptionUtil = encryptionUtil;
        this.signupValidator = signupValidator;
    }


    @Test
    @DisplayName("사용자 생성 및 조회")
    void create() {
        String name = "홍길동";
        String userId = "ghdrlfehd1234";
        String password = "rlfehddl1234";
        User user = null;

        Boolean signupValidation = signupValidator.validate(userId, password);
        if (signupValidation) {
            user = new User();
            user.setName(name);
            user.setUserId(userId);
            user.setPassword(encryptionUtil.encrypt(password));
        }

        userService.create(user);

        User createdUser = userService.getUserById(user.getId());

        Assertions.assertThat(createdUser.getName()).isEqualTo(name);
        Assertions.assertThat(createdUser.getUserId()).isEqualTo(userId);
        Assertions.assertThat(createdUser.getPassword()).isEqualTo(password);
    }

}