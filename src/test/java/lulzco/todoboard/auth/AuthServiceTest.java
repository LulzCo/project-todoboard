package lulzco.todoboard.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthServiceTest {

    private final AuthService authService;


    AuthServiceTest(AuthService authService) {
        this.authService = authService;
    }

    @Test
    void login() {
        
    }
}