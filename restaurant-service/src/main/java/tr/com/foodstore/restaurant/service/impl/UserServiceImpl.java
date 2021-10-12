package tr.com.foodstore.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.restaurant.exception.user.UserNotExistException;
import tr.com.foodstore.restaurant.model.domain.User;
import tr.com.foodstore.restaurant.repository.UserRepository;
import tr.com.foodstore.restaurant.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UserNotExistException("The user not exist!");
        }
        return user;
    }
}
