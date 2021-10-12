package tr.com.foodstore.orderservice.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.orderservice.exception.BaseException;
import tr.com.foodstore.orderservice.exception.order.OrderAlreadyPlacedException;
import tr.com.foodstore.orderservice.model.domain.Food;
import tr.com.foodstore.orderservice.model.domain.Order;
import tr.com.foodstore.orderservice.model.dto.OrderDto;
import tr.com.foodstore.orderservice.repository.OrderRepository;
import tr.com.foodstore.orderservice.service.FoodService;
import tr.com.foodstore.orderservice.service.OrderService;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public void createOrder(Order order) throws BaseException {
        if (orderRepository.findById(order.getId()).isPresent()) {
            throw new OrderAlreadyPlacedException("The order already placed!");
        }
        orderRepository.save(order);
    }

    @Override
    public Order transformOrderFromOrderDto(OrderDto orderDto) throws BaseException {
        Order order = new Order();
        Set<Food> foods = new HashSet<>();
        if (CollectionUtils.isNotEmpty(orderDto.getFoodIds())) {
            orderDto.getFoodIds().forEach(foodId -> foods.add(foodService.getFoodById(foodId)));
        }
        order.setFoods(foods);
        return order;
    }
}
