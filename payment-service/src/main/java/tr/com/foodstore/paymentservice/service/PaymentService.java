package tr.com.foodstore.paymentservice.service;

import tr.com.foodstore.paymentservice.exception.BaseException;
import tr.com.foodstore.paymentservice.model.dto.PaymentDto;

public interface PaymentService {
    void validatePayment(PaymentDto paymentDto) throws BaseException;
}
