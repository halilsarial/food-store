package tr.com.foodstore.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.userservice.model.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
