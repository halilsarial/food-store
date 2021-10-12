package tr.com.foodstore.paymentservice.exception.creditcard;

import tr.com.foodstore.paymentservice.exception.NotExistException;

public class CreditCardNotExistException extends NotExistException {
    public CreditCardNotExistException(String message) {
        super(message);
    }
}
