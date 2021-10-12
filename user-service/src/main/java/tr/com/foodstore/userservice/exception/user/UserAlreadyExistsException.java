package tr.com.foodstore.userservice.exception.user;

import tr.com.foodstore.userservice.exception.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
