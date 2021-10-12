package tr.com.foodstore.userservice.exception.creditcard;

import tr.com.foodstore.userservice.exception.NotExistException;

public class CreditCardNotExistException extends NotExistException {
    public CreditCardNotExistException(String message) {
        super(message);
    }
}
