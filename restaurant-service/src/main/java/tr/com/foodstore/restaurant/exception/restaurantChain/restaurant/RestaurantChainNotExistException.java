package tr.com.foodstore.restaurant.exception.restaurantChain.restaurant;

import tr.com.foodstore.restaurant.exception.NotExistException;

public class RestaurantChainNotExistException extends NotExistException {
    public RestaurantChainNotExistException(String message) {
        super(message);
    }
}
