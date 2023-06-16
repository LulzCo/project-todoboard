package lulzco.todoboard.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) throws DuplicateIdException {
        if (isIdDuplicated(user.getUserId())) {
            throw new DuplicateIdException("아이디가 이미 사용 중입니다.");
        }
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        Optional<User> found = userRepository.findById(id);
        if (found.isPresent()) {
            user = found.get();
        }
        return user;
    }

    @Override
    public boolean isIdDuplicated(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
