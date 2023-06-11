package lulzco.todoboard.user;

public class DuplicateIdException extends Throwable {
    public DuplicateIdException(String message) {
        super(message);
    }
}
