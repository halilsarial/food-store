package tr.com.foodstore.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.orderservice.model.domain.Food;
import tr.com.foodstore.orderservice.exception.BaseException;
import tr.com.foodstore.orderservice.exception.food.FoodNotExistException;
import tr.com.foodstore.orderservice.repository.FoodRepository;
import tr.com.foodstore.orderservice.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food getFoodById(Long id) throws BaseException {
        Food food = foodRepository.findById(id).orElse(null);
        if(food == null){
            throw new FoodNotExistException("The food not exist!");
        }
        return food;
    }
}
