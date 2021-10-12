package tr.com.foodstore.restaurant.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.restaurant.exception.restaurantChain.restaurant.RestaurantChainAlreadyExistsException;
import tr.com.foodstore.restaurant.exception.restaurantChain.restaurant.RestaurantChainNotExistException;
import tr.com.foodstore.restaurant.model.domain.Food;
import tr.com.foodstore.restaurant.model.domain.Menu;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.service.FoodService;
import tr.com.foodstore.restaurant.service.MenuService;
import tr.com.foodstore.restaurant.service.RestaurantChainService;
import tr.com.foodstore.restaurant.service.RestaurantService;

import java.util.Set;

@Service
public class RestaurantChainServiceImpl implements RestaurantChainService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private FoodService foodService;

    @Override
    public Set<Restaurant> getAllChildRestaurants(Restaurant restaurant, Set<Restaurant> allChildRestaurants) {
        Set<Restaurant> childRestaurants = restaurantService.findChildRestaurants(restaurant);
        if (CollectionUtils.isNotEmpty(childRestaurants)) {
            for (Restaurant childRestaurant : childRestaurants) {
                allChildRestaurants.add(childRestaurant);
                allChildRestaurants = getAllChildRestaurants(childRestaurant, allChildRestaurants);
            }
        }
        return allChildRestaurants;
    }

    @Override
    public void connectRestaurantToChain(Restaurant restaurantChain, Restaurant restaurant) {
        if (restaurant.getRestaurantChain() != null && restaurant.getRestaurantChain().getId().equals(restaurantChain.getId())) {
            throw new RestaurantChainAlreadyExistsException("The restaurant is connected already");
        }
        restaurant.setRestaurantChain(restaurantChain);
        if (CollectionUtils.isEmpty(restaurantChain.getMenus())) {
            return;
        }
        Set<Menu> restaurantChainMenus = restaurantChain.getMenus();
        for (Menu restaurantChainMenu : restaurantChainMenus) {
            createOrUpdateChildRestaurantMenuFromRestaurantChain(restaurant, restaurantChainMenu);
        }
    }

    @Override
    public void removeRestaurantFromChain(Restaurant restaurantChain, Restaurant restaurant) {
        if (restaurant.getRestaurantChain() == null) {
            throw new RestaurantChainNotExistException("The restaurant is not connected the chain!");
        } else if (restaurant.getRestaurantChain() != null && !restaurant.getRestaurantChain().getId().equals(restaurantChain.getId())) {
            throw new RestaurantChainNotExistException("The restaurant is connected another chain!");
        }
        restaurant.setRestaurantChain(null);
    }

    @Override
    public void createOrUpdateChildRestaurantMenuFromRestaurantChain(Restaurant childRestaurant, Menu restaurantChainMenu) {
        boolean flag = false;
        for (Menu restaurantMenu : childRestaurant.getMenus()) {
            if (restaurantMenu.getName().equals(restaurantChainMenu.getName())) {
                flag = true;
                updateMenuFromRestaurantChain(childRestaurant, restaurantChainMenu, restaurantMenu);
            }
        }
        if (!flag) {
            createMenuFromRestaurantChain(childRestaurant, restaurantChainMenu);
        }
    }

    @Override
    public void createMenuFromRestaurantChain(Restaurant restaurant, Menu restaurantChainMenu) {
        Menu menu = new Menu();
        menu.setName(restaurantChainMenu.getName());
        menu.setRestaurant(restaurant);
        if (CollectionUtils.isNotEmpty(restaurantChainMenu.getFoods())) {
            Set<Food> restaurantChainFoods = restaurantChainMenu.getFoods();
            restaurantChainFoods.forEach(restaurantChainFood -> createFoodFromRestaurantChain(menu, restaurantChainFood));
        }
        menuService.createMenu(menu);
    }

    @Override
    public void deleteMenuFromRestaurantChain(Restaurant restaurant, Menu restaurantChainMenu) {
        if (CollectionUtils.isNotEmpty(restaurant.getMenus())) {
            restaurant.getMenus().stream().filter(menu -> menu.getName().equals(restaurantChainMenu.getName())).forEach(menu -> menuService.deleteMenu(menu));
        }
    }

    @Override
    public void updateMenuFromRestaurantChain(Restaurant restaurant, Menu restaurantChainMenu, Menu restaurantMenu) {
        if (CollectionUtils.isNotEmpty(restaurantChainMenu.getFoods())) {
            for (Food restaurantChainFood : restaurantChainMenu.getFoods()) {
                createOrUpdateChildRestaurantFoodFromRestaurantChain(restaurantMenu, restaurantChainFood);
            }
        }
    }

    @Override
    public void createOrUpdateChildRestaurantFoodFromRestaurantChain(Menu childRestaurantMenu, Food restaurantChainFood) {
        boolean flag = false;
        for (Food restaurantFood : childRestaurantMenu.getFoods()) {
            flag = true;
            updateFoodFromRestaurantChain(childRestaurantMenu, restaurantChainFood, restaurantFood);
        }
        if (!flag) {
            createFoodFromRestaurantChain(childRestaurantMenu, restaurantChainFood);
        }
    }

    @Override
    public void createFoodFromRestaurantChain(Menu restaurantMenu, Food restaurantChainFood) {
        Food food = new Food();
        food.setName(restaurantChainFood.getName());
        food.setPrice(restaurantChainFood.getPrice());
        food.setMenu(restaurantMenu);
        foodService.createFood(food);
    }

    @Override
    public void updateFoodFromRestaurantChain(Menu restaurantMenu, Food restaurantChainFood, Food restaurantFood) {
        restaurantFood.setPrice(restaurantChainFood.getPrice());
        foodService.updateFood(restaurantFood, restaurantChainFood.getName());
    }

    @Override
    public void deleteFoodFromRestaurantChain(Restaurant childRestaurant, Food restaurantChainFood) {
        if (CollectionUtils.isNotEmpty(childRestaurant.getMenus())) {
            childRestaurant.getMenus().stream()
                    .filter(childRestaurantMenu -> childRestaurantMenu.getName().equals(restaurantChainFood.getMenu().getName()))
                    .forEach(childRestaurantMenu -> childRestaurantMenu.getFoods().stream()
                            .filter(childRestaurantMenuFood -> childRestaurantMenuFood.getName().equals(restaurantChainFood.getName()))
                            .forEach(childRestaurantMenuFood -> foodService.deleteFood(childRestaurantMenuFood)));
        }
    }


}
