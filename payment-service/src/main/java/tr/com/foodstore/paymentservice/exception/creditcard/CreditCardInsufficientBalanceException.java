package tr.com.foodstore.paymentservice.exception.creditcard;

import tr.com.foodstore.paymentservice.exception.NotAcceptableException;

public class CreditCardInsufficientBalanceException extends NotAcceptableException {
    public CreditCardInsufficientBalanceException(String message) {
        super(message);
    }
}
