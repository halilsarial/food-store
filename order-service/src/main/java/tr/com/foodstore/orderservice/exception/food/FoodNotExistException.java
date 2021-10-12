package tr.com.foodstore.orderservice.exception.food;

import tr.com.foodstore.orderservice.exception.NotExistException;

public class FoodNotExistException extends NotExistException {
    public FoodNotExistException(String message) {
        super(message);
    }
}
