package tr.com.foodstore.userservice.exception.creditcard;

import tr.com.foodstore.userservice.exception.AlreadyExistsException;

public class CreditCardAlreadyExistsException extends AlreadyExistsException {
    public CreditCardAlreadyExistsException(String message) {
        super(message);
    }
}
