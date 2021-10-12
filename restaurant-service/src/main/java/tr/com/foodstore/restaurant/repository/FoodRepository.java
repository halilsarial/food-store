package tr.com.foodstore.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.foodstore.restaurant.model.domain.Food;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {
}
