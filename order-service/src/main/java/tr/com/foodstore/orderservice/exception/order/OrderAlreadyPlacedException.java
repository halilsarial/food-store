package tr.com.foodstore.orderservice.exception.order;

import tr.com.foodstore.orderservice.exception.AlreadyExistsException;

public class OrderAlreadyPlacedException extends AlreadyExistsException {
    public OrderAlreadyPlacedException(String message) {
        super(message);
    }
}
