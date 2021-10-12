package tr.com.foodstore.paymentservice.service;

import tr.com.foodstore.paymentservice.model.domain.CreditCard;

public interface CreditCardService {
    CreditCard getCreditCardByCardNo(Long cardNo);

    void updateCreditCardBalance(CreditCard creditCard);
}
