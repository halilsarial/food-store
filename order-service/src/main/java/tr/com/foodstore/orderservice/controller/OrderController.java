package tr.com.foodstore.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.foodstore.orderservice.model.dto.OrderDto;
import tr.com.foodstore.orderservice.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(OrderDto orderDto) {
        orderService.createOrder(orderService.transformOrderFromOrderDto(orderDto));
        return ResponseEntity.ok().body("The order placed successfully!");
    }
}
