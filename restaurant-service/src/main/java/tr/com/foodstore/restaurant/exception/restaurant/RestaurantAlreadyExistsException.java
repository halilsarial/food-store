package tr.com.foodstore.restaurant.exception.restaurant;

import tr.com.foodstore.restaurant.exception.AlreadyExistsException;

public class RestaurantAlreadyExistsException extends AlreadyExistsException {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
