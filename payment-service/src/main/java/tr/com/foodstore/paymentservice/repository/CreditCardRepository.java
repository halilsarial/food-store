package tr.com.foodstore.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.paymentservice.model.domain.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    CreditCard getCreditCardByCardNo(Long cardNo);
}
