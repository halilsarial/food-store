package tr.com.foodstore.userservice.exception.user;

import tr.com.foodstore.userservice.exception.NotExistException;

public class UserNotExistException extends NotExistException {
    public UserNotExistException(String message) {
        super(message);
    }
}
