package tr.com.foodstore.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.foodstore.restaurant.model.domain.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
}
