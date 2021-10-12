package tr.com.foodstore.paymentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.paymentservice.exception.creditcard.CreditCardNotAcceptableException;
import tr.com.foodstore.paymentservice.exception.creditcard.CreditCardNotExistException;
import tr.com.foodstore.paymentservice.model.domain.CreditCard;
import tr.com.foodstore.paymentservice.repository.CreditCardRepository;
import tr.com.foodstore.paymentservice.service.CreditCardService;

import java.util.Date;

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
        if (creditCard.getExpirationDate().before(new Date())) {
            throw new CreditCardNotAcceptableException("The credit card expired!");
        }
        return creditCard;
    }

    @Override
    public void updateCreditCardBalance(CreditCard creditCard) {
        if (creditCardRepository.findById(creditCard.getId()).isEmpty()) {
            throw new CreditCardNotExistException("The credit card not exist!");
        }
        creditCardRepository.save(creditCard);
    }
}
