package tr.com.foodstore.restaurant.exception.restaurant;

import tr.com.foodstore.restaurant.exception.NotExistException;

public class RestaurantNotExistException extends NotExistException {
    public RestaurantNotExistException(String message) {
        super(message);
    }
}
