package lulzco.todoboard.user;

public interface UserService {
    void create(User user) throws DuplicateIdException;

    User getUserById(Long id);

    boolean isIdDuplicated(String userId);

    User getUserByUserId(String userId);
}
