package tr.com.foodstore.restaurant.exception.menu;

import tr.com.foodstore.restaurant.exception.NotExistException;

public class MenuNotExistException extends NotExistException {
    public MenuNotExistException(String message) {
        super(message);
    }
}
