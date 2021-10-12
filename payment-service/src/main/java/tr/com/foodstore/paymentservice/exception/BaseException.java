package tr.com.foodstore.paymentservice.exception;

public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
