package tr.com.foodstore.restaurant.service;

import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.model.domain.Food;
import tr.com.foodstore.restaurant.model.dto.FoodDto;

public interface FoodService {
    void createFood(Food food) throws BaseException;

    void deleteFood(Food food) throws BaseException;

    void updateFood(Food food, String oldName) throws BaseException;

    Food getFoodById(Long id);

    FoodDto transformDtoFromFood(Food food);

    Food transformFoodFromDto(FoodDto foodDto);
}
