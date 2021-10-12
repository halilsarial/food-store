package tr.com.foodstore.userservice.exception;

public class AlreadyExistsException extends BaseException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
