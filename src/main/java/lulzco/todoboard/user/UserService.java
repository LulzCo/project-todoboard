package lulzco.todoboard.user;

public interface UserService {
    void create(User user);

    User getUserById(Long id);
}
