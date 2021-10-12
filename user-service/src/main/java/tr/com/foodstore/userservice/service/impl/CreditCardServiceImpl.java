package tr.com.foodstore.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.userservice.exception.creditcard.CreditCardAlreadyExistsException;
import tr.com.foodstore.userservice.exception.creditcard.CreditCardNotExistException;
import tr.com.foodstore.userservice.service.CreditCardService;
import tr.com.foodstore.userservice.exception.BaseException;
import tr.com.foodstore.userservice.model.domain.CreditCard;
import tr.com.foodstore.userservice.repository.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public void createCreditCard(CreditCard creditCard) throws BaseException {
        if (creditCardRepository.findById(creditCard.getId()).isPresent() || creditCardRepository.getCreditCardByCardNo(creditCard.getCardNo()) != null) {
            throw new CreditCardAlreadyExistsException("The credit card already exists!");
        }
        creditCardRepository.save(creditCard);
    }

    @Override
    public void updateCreditCard(CreditCard creditCard) throws BaseException {
        if (creditCardRepository.findById(creditCard.getId()).isEmpty() || creditCardRepository.getCreditCardByCardNo(creditCard.getCardNo()) == null) {
            throw new CreditCardNotExistException("The credit card not exist!");
        }
        creditCardRepository.save(creditCard);
    }

    @Override
    public void deleteCreditCard(CreditCard creditCard) throws BaseException {
        if (creditCardRepository.findById(creditCard.getId()).isEmpty() || creditCardRepository.getCreditCardByCardNo(creditCard.getCardNo()) == null) {
            throw new CreditCardNotExistException("The credit card not exist!");
        }
        creditCardRepository.delete(creditCard);
    }

    @Override
    public CreditCard getCreditCardByCardNo(Long cardNo) throws BaseException {
        CreditCard creditCard = creditCardRepository.getCreditCardByCardNo(cardNo);
        if (creditCard == null) {
            throw new CreditCardNotExistException("The credit card not exist!");
        }
        return creditCard;
    }
}
