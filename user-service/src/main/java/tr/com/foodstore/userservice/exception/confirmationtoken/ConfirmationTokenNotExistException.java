package tr.com.foodstore.userservice.exception.confirmationtoken;

import tr.com.foodstore.userservice.exception.NotExistException;

public class ConfirmationTokenNotExistException extends NotExistException {
    public ConfirmationTokenNotExistException(String message) {
        super(message);
    }
}
