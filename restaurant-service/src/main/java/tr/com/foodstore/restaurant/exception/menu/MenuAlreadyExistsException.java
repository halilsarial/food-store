package tr.com.foodstore.restaurant.exception.menu;

import tr.com.foodstore.restaurant.exception.AlreadyExistsException;

public class MenuAlreadyExistsException extends AlreadyExistsException {
    public MenuAlreadyExistsException(String message) {
        super(message);
    }
}
