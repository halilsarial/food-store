package tr.com.foodstore.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.foodstore.userservice.exception.BaseException;
import tr.com.foodstore.userservice.exception.user.UserAlreadyExistsException;
import tr.com.foodstore.userservice.exception.user.UserNotExistException;
import tr.com.foodstore.userservice.model.domain.User;
import tr.com.foodstore.userservice.model.domain.UserRole;
import tr.com.foodstore.userservice.model.dto.userservice.UserDto;
import tr.com.foodstore.userservice.repository.UserRepository;
import tr.com.foodstore.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(User user) throws BaseException {
        if (user.getId() != null && userRepository.findById(user.getId()).isPresent() || userRepository.findByUserName(user.getUserName()) != null) {
            throw new UserAlreadyExistsException("The user already exists!");
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) throws BaseException {
        if (userRepository.findById(user.getId()).isEmpty() || userRepository.findByUserName(user.getUserName()) == null) {
            throw new UserNotExistException("The user not exist!");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) throws BaseException {
        if (userRepository.findById(user.getId()).isEmpty() || userRepository.findByUserName(user.getUserName()) == null) {
            throw new UserNotExistException("The user not exist!");
        }
        userRepository.delete(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UserNotExistException("The user not exist!");
        }
        return user;
    }

    @Override
    public User transformUserFromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUserRole(UserRole.USER);
        return user;
    }
}
