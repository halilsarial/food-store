package tr.com.foodstore.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.restaurant.model.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
