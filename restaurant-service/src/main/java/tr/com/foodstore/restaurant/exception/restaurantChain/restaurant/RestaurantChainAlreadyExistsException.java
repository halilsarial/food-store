package tr.com.foodstore.restaurant.exception.restaurantChain.restaurant;

import tr.com.foodstore.restaurant.exception.AlreadyExistsException;

public class RestaurantChainAlreadyExistsException extends AlreadyExistsException {
    public RestaurantChainAlreadyExistsException(String message) {
        super(message);
    }
}
