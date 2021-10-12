package tr.com.foodstore.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.foodstore.paymentservice.model.dto.PaymentDto;
import tr.com.foodstore.paymentservice.service.PaymentService;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public void makePayment(PaymentDto paymentDto) {
        paymentService.validatePayment(paymentDto);
    }
}
