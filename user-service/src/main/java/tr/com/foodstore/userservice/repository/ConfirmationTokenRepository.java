package tr.com.foodstore.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.userservice.model.domain.ConfirmationToken;

import java.util.Date;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationTokenAndCreationTimeAfter(String confirmationToken, Date creationTime);
}
