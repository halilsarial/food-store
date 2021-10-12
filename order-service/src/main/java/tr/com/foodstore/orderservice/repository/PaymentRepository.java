package tr.com.foodstore.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.orderservice.model.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
