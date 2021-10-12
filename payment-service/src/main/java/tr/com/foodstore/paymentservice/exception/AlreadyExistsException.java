package tr.com.foodstore.paymentservice.exception;

public class AlreadyExistsException extends BaseException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
