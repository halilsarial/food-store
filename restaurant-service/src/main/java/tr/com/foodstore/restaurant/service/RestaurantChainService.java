package tr.com.foodstore.restaurant.service;

import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.model.domain.Food;
import tr.com.foodstore.restaurant.model.domain.Menu;
import tr.com.foodstore.restaurant.model.domain.Restaurant;

import java.util.Set;

public interface RestaurantChainService {

    Set<Restaurant> getAllChildRestaurants(Restaurant restaurant, Set<Restaurant> allChildRestaurants);

    void connectRestaurantToChain(Restaurant restaurantChain, Restaurant restaurant) throws BaseException;

    void removeRestaurantFromChain(Restaurant restaurantChain, Restaurant restaurant);

    void createOrUpdateChildRestaurantMenuFromRestaurantChain(Restaurant childRestaurant, Menu restaurantChainMenu);

    void createMenuFromRestaurantChain(Restaurant restaurant, Menu restaurantChainMenu);

    void deleteMenuFromRestaurantChain(Restaurant restaurant, Menu restaurantChainMenu);

    void updateMenuFromRestaurantChain(Restaurant restaurant, Menu restaurantChainMenu, Menu restaurantMenu);

    void createOrUpdateChildRestaurantFoodFromRestaurantChain(Menu childRestaurantMenu, Food restaurantChainFood);

    void createFoodFromRestaurantChain(Menu restaurantMenu, Food restaurantChainFood);

    void updateFoodFromRestaurantChain(Menu restaurantMenu, Food restaurantChainFood, Food restaurantFood);

    void deleteFoodFromRestaurantChain(Restaurant childRestaurant, Food restaurantChainFood);
}
