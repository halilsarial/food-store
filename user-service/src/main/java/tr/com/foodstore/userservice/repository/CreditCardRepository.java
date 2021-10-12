package tr.com.foodstore.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import tr.com.foodstore.userservice.model.domain.CreditCard;

public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    CreditCard getCreditCardByCardNo(Long cardNo);
}
