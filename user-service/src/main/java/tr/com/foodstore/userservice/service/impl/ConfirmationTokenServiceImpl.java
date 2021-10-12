package tr.com.foodstore.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.com.foodstore.userservice.exception.BaseException;
import tr.com.foodstore.userservice.exception.confirmationtoken.ConfirmationTokenNotExistException;
import tr.com.foodstore.userservice.model.domain.ConfirmationToken;
import tr.com.foodstore.userservice.model.domain.User;
import tr.com.foodstore.userservice.repository.ConfirmationTokenRepository;
import tr.com.foodstore.userservice.service.ConfirmationTokenService;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Value("${confirmation.token.expire.time}")
    private String expireTime;

    @Override
    public ConfirmationToken findByConfirmationToken(String token) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, -(Integer.parseInt(expireTime)));
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationTokenAndCreationTimeAfter(token, calendar.getTime());
        if (confirmationToken == null) {
            throw new ConfirmationTokenNotExistException("Your session has been expired. Please re-sign in!");
        }
        return confirmationToken;
    }

    @Override
    public ConfirmationToken produceConfirmationToken(User user) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setUser(user);
        confirmationToken.setConfirmationToken(UUID.randomUUID().toString());
        return confirmationToken;
    }

    @Override
    public void createConfirmationToken(ConfirmationToken confirmationToken) throws BaseException {
        confirmationTokenRepository.save(confirmationToken);
    }
}
