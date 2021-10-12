package tr.com.foodstore.orderservice.exception;

public class AlreadyExistsException extends BaseException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
