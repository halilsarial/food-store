package tr.com.foodstore.restaurant.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.exception.food.FoodAlreadyExistsException;
import tr.com.foodstore.restaurant.exception.food.FoodNotExistException;
import tr.com.foodstore.restaurant.model.domain.Food;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.FoodDto;
import tr.com.foodstore.restaurant.repository.FoodRepository;
import tr.com.foodstore.restaurant.service.FoodService;
import tr.com.foodstore.restaurant.service.RestaurantChainService;

import java.util.HashSet;
import java.util.Set;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantChainService restaurantChainService;

    @Override
    public void createFood(Food food) throws BaseException {
        if (getFoodById(food.getId()) != null) {
            throw new FoodAlreadyExistsException("The food already exists!");
        } else {
            Restaurant restaurant = food.getMenu().getRestaurant();
            Set<Restaurant> childRestaurants = restaurantChainService.getAllChildRestaurants(restaurant, new HashSet<>());
            if (CollectionUtils.isNotEmpty(childRestaurants)) {
                restaurantChainService.createOrUpdateChildRestaurantFoodFromRestaurantChain(food.getMenu(), food);
            }
            foodRepository.save(food);
        }
    }

    @Override
    public void deleteFood(Food food) throws BaseException {
        if (getFoodById(food.getId()) == null) {
            throw new FoodNotExistException("The food does not exist!");
        } else {
            Set<Restaurant> childRestaurants = restaurantChainService.getAllChildRestaurants(food.getMenu().getRestaurant(), new HashSet<>());
            if (CollectionUtils.isNotEmpty(childRestaurants)) {
                childRestaurants.forEach(childRestaurant -> restaurantChainService.deleteFoodFromRestaurantChain(childRestaurant, food));
            }
            foodRepository.delete(food);
        }
    }

    @Override
    public void updateFood(Food food, String oldName) throws BaseException {
        if (getFoodById(food.getId()) == null) {
            throw new FoodNotExistException("The food does not exist!");
        } else {
            Set<Restaurant> childRestaurants = restaurantChainService.getAllChildRestaurants(food.getMenu().getRestaurant(), new HashSet<>());
            if (CollectionUtils.isNotEmpty(childRestaurants)) {
                childRestaurants
                        .forEach(childRestaurant -> childRestaurant.getMenus().stream()
                                .filter(childRestaurantMenu -> childRestaurantMenu.getName().equals(food.getMenu().getName()))
                                .forEach(childRestaurantMenu -> {
                                    childRestaurantMenu.getFoods().stream()
                                            .filter(childRestaurantMenuFood -> childRestaurantMenuFood.getName().equals(oldName))
                                            .forEach(childRestaurantMenuFood -> {
                                                childRestaurantMenuFood.setName(food.getName());
                                                childRestaurantMenuFood.setPrice(food.getPrice());
                                                foodRepository.save(childRestaurantMenuFood);
                                            });
                                }));
            }
            foodRepository.save(food);
        }
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public FoodDto transformDtoFromFood(Food food) {
        FoodDto foodDto = new FoodDto();
        foodDto.setId(food.getId());
        foodDto.setName(food.getName());
        foodDto.setPrice(food.getPrice());
        return foodDto;
    }

    @Override
    public Food transformFoodFromDto(FoodDto foodDto) {
        Food food = new Food();
        food.setName(foodDto.getName());
        food.setPrice(foodDto.getPrice());
        return food;
    }
}
