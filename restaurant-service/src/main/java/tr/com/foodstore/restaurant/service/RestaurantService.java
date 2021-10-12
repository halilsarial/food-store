package tr.com.foodstore.restaurant.service;

import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.RestaurantDto;

import java.util.List;
import java.util.Set;

public interface RestaurantService {
    void createRestaurant(Restaurant restaurant) throws BaseException;

    void updateRestaurant(Restaurant restaurant) throws BaseException;

    void deleteRestaurant(Restaurant restaurant) throws BaseException;

    void deleteRestaurantById(Long id) throws BaseException;

    List<RestaurantDto> getAllRestaurants();

    Restaurant getRestaurantById(Long id) throws BaseException;

    Restaurant getRestaurantByName(String name) throws BaseException;

    Set<Restaurant> findChildRestaurants(Restaurant restaurantChain);

    Restaurant transformRestaurantFromDto(RestaurantDto restaurantDto);

    RestaurantDto transformDtoFromRestaurant(Restaurant restaurant);
}
