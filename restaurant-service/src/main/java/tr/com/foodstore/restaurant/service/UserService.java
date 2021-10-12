package tr.com.foodstore.restaurant.service;

import tr.com.foodstore.restaurant.model.domain.User;

public interface UserService {

    User getUserByUserName(String userName);
}
