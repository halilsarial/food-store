package tr.com.foodstore.orderservice.service;

import tr.com.foodstore.orderservice.model.domain.Order;
import tr.com.foodstore.orderservice.model.dto.OrderDto;
import tr.com.foodstore.orderservice.exception.BaseException;

public interface OrderService {

    void createOrder(Order order) throws BaseException;

    Order transformOrderFromOrderDto(OrderDto orderDto) throws BaseException;
}
