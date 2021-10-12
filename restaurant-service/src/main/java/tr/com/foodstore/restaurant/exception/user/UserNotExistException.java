package tr.com.foodstore.restaurant.exception.user;

import tr.com.foodstore.restaurant.exception.NotExistException;

public class UserNotExistException extends NotExistException {
    public UserNotExistException(String message) {
        super(message);
    }
}
