package tr.com.foodstore.restaurant.exception.food;

import tr.com.foodstore.restaurant.exception.NotExistException;

public class FoodNotExistException extends NotExistException {
    public FoodNotExistException(String message) {
        super(message);
    }
}
