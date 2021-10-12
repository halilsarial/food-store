package tr.com.foodstore.orderservice.exception;

public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
