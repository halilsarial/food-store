package tr.com.foodstore.orderservice.exception.user;

import tr.com.foodstore.orderservice.exception.NotExistException;

public class UserNotExistException extends NotExistException {
    public UserNotExistException(String message) {
        super(message);
    }
}
