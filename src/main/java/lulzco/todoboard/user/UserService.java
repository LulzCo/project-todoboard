package lulzco.todoboard.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    void create(User user) throws DuplicateIdException;

    User getUserById(Long id);

    boolean isIdDuplicated(String userId);

    User getUserByUserId(String userId);

    PasswordEncoder passwordEncoder();
}
