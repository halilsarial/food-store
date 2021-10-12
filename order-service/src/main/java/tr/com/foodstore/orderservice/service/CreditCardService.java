package tr.com.foodstore.orderservice.service;

import tr.com.foodstore.orderservice.model.domain.CreditCard;

public interface CreditCardService {
    CreditCard getCreditCardByCardNo(Long cardNo);
}
