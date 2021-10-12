package tr.com.foodstore.paymentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.paymentservice.exception.BaseException;
import tr.com.foodstore.paymentservice.exception.creditcard.CreditCardInsufficientBalanceException;
import tr.com.foodstore.paymentservice.model.domain.CreditCard;
import tr.com.foodstore.paymentservice.model.dto.PaymentDto;
import tr.com.foodstore.paymentservice.service.CreditCardService;
import tr.com.foodstore.paymentservice.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CreditCardService creditCardService;

    @Override
    public void validatePayment(PaymentDto paymentDto) throws BaseException {
        CreditCard creditCard = creditCardService.getCreditCardByCardNo(paymentDto.getCardNo());
        if (creditCard.getAmount() < paymentDto.getTotalAmount()) {
            throw new CreditCardInsufficientBalanceException("Insufficient card balance!");
        }
        creditCard.setAmount(creditCard.getAmount() - paymentDto.getTotalAmount());
        creditCardService.updateCreditCardBalance(creditCard);
    }
}
