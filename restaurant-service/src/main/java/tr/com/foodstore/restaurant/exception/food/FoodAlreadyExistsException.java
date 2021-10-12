package tr.com.foodstore.restaurant.exception.food;

import tr.com.foodstore.restaurant.exception.AlreadyExistsException;

public class FoodAlreadyExistsException extends AlreadyExistsException {
    public FoodAlreadyExistsException(String message) {
        super(message);
    }
}
