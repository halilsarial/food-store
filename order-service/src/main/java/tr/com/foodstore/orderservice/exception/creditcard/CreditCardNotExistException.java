package tr.com.foodstore.orderservice.exception.creditcard;

import tr.com.foodstore.orderservice.exception.NotExistException;

public class CreditCardNotExistException extends NotExistException {
    public CreditCardNotExistException(String message) {
        super(message);
    }
}
