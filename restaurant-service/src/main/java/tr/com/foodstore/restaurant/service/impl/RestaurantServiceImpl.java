package tr.com.foodstore.restaurant.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.exception.restaurant.RestaurantAlreadyExistsException;
import tr.com.foodstore.restaurant.exception.restaurant.RestaurantNotExistException;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.RestaurantDto;
import tr.com.foodstore.restaurant.repository.RestaurantRepository;
import tr.com.foodstore.restaurant.service.MenuService;
import tr.com.foodstore.restaurant.service.RestaurantService;
import tr.com.foodstore.restaurant.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Override
    public void createRestaurant(Restaurant restaurant) throws BaseException {
        if (restaurant.getId() != null && getRestaurantById(restaurant.getId()) != null) {
            throw new RestaurantAlreadyExistsException("The restaurant already exists}!");
        } else {
            restaurantRepository.save(restaurant);
        }
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) throws BaseException {
        if (getRestaurantById(restaurant.getId()) == null) {
            throw new RestaurantNotExistException("The restaurant does not exist!");
        } else {
            deleteRestaurantAndMenus(restaurant);
        }
    }

    private void deleteRestaurantAndMenus(Restaurant restaurant) {
        Set<Restaurant> childRestaurants = findChildRestaurants(restaurant);
        childRestaurants.forEach(childRestaurant -> {
            childRestaurant.setRestaurantChain(null);
            updateRestaurant(childRestaurant);
        });
        restaurant.getMenus().forEach(menu -> menuService.deleteMenu(menu));
        restaurantRepository.delete(restaurant);
    }

    @Override
    public void deleteRestaurantById(Long id) throws BaseException {
        Restaurant restaurant = getRestaurantById(id);
        if (restaurant == null) {
            throw new RestaurantNotExistException("The restaurant does not exist!");
        } else {
            deleteRestaurantAndMenus(restaurant);
        }
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        restaurantRepository.findAll().spliterator().trySplit().forEachRemaining(restaurant -> restaurantDtos.add(transformDtoFromRestaurant(restaurant)));
        return restaurantDtos;
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) throws BaseException {
        if (getRestaurantById(restaurant.getId()) == null) {
            throw new RestaurantNotExistException("The restaurant does not exist!");
        } else {
            restaurantRepository.save(restaurant);
        }
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant == null) {
            throw new RestaurantNotExistException("The restaurant does not exist!");
        }
        return restaurant;
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        Restaurant restaurant = restaurantRepository.findRestaurantByName(name);
        if (restaurant == null) {
            throw new RestaurantNotExistException("The restaurant does not exist!");
        }
        return restaurant;
    }

    @Override
    public Set<Restaurant> findChildRestaurants(Restaurant restaurantChain) {
        return restaurantRepository.findAllByRestaurantChain(restaurantChain);
    }

    @Override
    public Restaurant transformRestaurantFromDto(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName().trim().toUpperCase());
        restaurant.setMenus(null);
        if (StringUtils.isNotEmpty(restaurantDto.getOwnerUserName())) {
            restaurant.setOwnerUser(userService.getUserByUserName(restaurantDto.getOwnerUserName()));
        }
        return restaurant;
    }

    @Override
    public RestaurantDto transformDtoFromRestaurant(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setName(restaurant.getName());
        return restaurantDto;
    }
}
