package tr.com.foodstore.userservice.service;

import tr.com.foodstore.userservice.exception.BaseException;
import tr.com.foodstore.userservice.model.domain.User;
import tr.com.foodstore.userservice.model.dto.userservice.UserDto;

public interface UserService {

    void createUser(User user) throws BaseException;

    void updateUser(User user) throws BaseException;

    void deleteUser(User user) throws BaseException;

    User getUserByUserName(String userName);

    User transformUserFromDto(UserDto userDto);
}
