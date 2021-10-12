package tr.com.foodstore.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.orderservice.model.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User getUserByUserName(String userName);
}
