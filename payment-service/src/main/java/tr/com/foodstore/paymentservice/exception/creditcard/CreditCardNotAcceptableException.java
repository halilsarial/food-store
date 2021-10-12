package tr.com.foodstore.paymentservice.exception.creditcard;

import tr.com.foodstore.paymentservice.exception.NotAcceptableException;

public class CreditCardNotAcceptableException extends NotAcceptableException {
    public CreditCardNotAcceptableException(String message) {
        super(message);
    }
}
