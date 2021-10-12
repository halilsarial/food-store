package tr.com.foodstore.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.orderservice.model.domain.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    CreditCard getCreditCardByCardNo(Long cardNo);
}
