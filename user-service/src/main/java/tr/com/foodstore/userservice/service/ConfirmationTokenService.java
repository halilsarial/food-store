package tr.com.foodstore.userservice.service;

import tr.com.foodstore.userservice.exception.BaseException;
import tr.com.foodstore.userservice.model.domain.ConfirmationToken;
import tr.com.foodstore.userservice.model.domain.User;

public interface ConfirmationTokenService {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

    ConfirmationToken produceConfirmationToken(User user);

    void createConfirmationToken(ConfirmationToken confirmationToken) throws BaseException;
}
