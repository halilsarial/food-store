package tr.com.foodstore.orderservice.service;

import tr.com.foodstore.orderservice.model.domain.Food;
import tr.com.foodstore.orderservice.exception.BaseException;

public interface FoodService {
    Food getFoodById(Long id) throws BaseException;
}
