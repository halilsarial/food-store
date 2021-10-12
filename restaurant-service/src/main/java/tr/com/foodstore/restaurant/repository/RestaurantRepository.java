package tr.com.foodstore.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.foodstore.restaurant.model.domain.Restaurant;

import java.util.Set;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Set<Restaurant> findAllByRestaurantChain(Restaurant restaurantChain);

    Restaurant findRestaurantByName(String name);
}
