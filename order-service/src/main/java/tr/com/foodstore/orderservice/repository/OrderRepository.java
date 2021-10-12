package tr.com.foodstore.orderservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.foodstore.orderservice.model.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
