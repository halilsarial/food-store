package tr.com.foodstore.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.orderservice.model.domain.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
}
