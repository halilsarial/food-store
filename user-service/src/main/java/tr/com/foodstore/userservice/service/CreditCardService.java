package tr.com.foodstore.userservice.service;

import tr.com.foodstore.userservice.exception.BaseException;
import tr.com.foodstore.userservice.model.domain.CreditCard;

public interface CreditCardService {

    void createCreditCard(CreditCard creditCard) throws BaseException;

    void updateCreditCard(CreditCard creditCard) throws BaseException;

    void deleteCreditCard(CreditCard creditCard) throws BaseException;

    CreditCard getCreditCardByCardNo(Long cardNo) throws BaseException;
}
