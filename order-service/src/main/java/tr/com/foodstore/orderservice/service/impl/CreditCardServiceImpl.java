package tr.com.foodstore.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.orderservice.exception.creditcard.CreditCardNotExistException;
import tr.com.foodstore.orderservice.model.domain.CreditCard;
import tr.com.foodstore.orderservice.repository.CreditCardRepository;
import tr.com.foodstore.orderservice.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard getCreditCardByCardNo(Long cardNo) {
        CreditCard creditCard = creditCardRepository.getCreditCardByCardNo(cardNo);
        if (creditCard == null) {
            throw new CreditCardNotExistException("The credit card not exist!");
        }
        return creditCard;
    }
}
