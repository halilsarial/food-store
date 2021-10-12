package tr.com.foodstore.restaurant.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.foodstore.restaurant.exception.BaseException;
import tr.com.foodstore.restaurant.exception.menu.MenuAlreadyExistsException;
import tr.com.foodstore.restaurant.exception.menu.MenuNotExistException;
import tr.com.foodstore.restaurant.model.domain.Menu;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.MenuDto;
import tr.com.foodstore.restaurant.repository.MenuRepository;
import tr.com.foodstore.restaurant.service.FoodService;
import tr.com.foodstore.restaurant.service.MenuService;
import tr.com.foodstore.restaurant.service.RestaurantChainService;

import java.util.HashSet;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantChainService restaurantChainService;

    @Override
    public void createMenu(Menu menu) throws BaseException {
        if (getMenuById(menu.getId()) != null) {
            throw new MenuAlreadyExistsException("The menu already exists}!");
        } else {
            Set<Restaurant> childRestaurants = restaurantChainService.getAllChildRestaurants(menu.getRestaurant(), new HashSet<>());
            if (CollectionUtils.isNotEmpty(childRestaurants)) {
                childRestaurants.forEach(childRestaurant -> restaurantChainService.createOrUpdateChildRestaurantMenuFromRestaurantChain(childRestaurant, menu));
            }
            menuRepository.save(menu);
        }
    }

    @Override
    public void deleteMenu(Menu menu) throws BaseException {
        if (getMenuById(menu.getId()) == null) {
            throw new MenuNotExistException("The menu does not exist!");
        } else {
            if (CollectionUtils.isNotEmpty(menu.getFoods())) {
                menu.getFoods().forEach(food -> foodService.deleteFood(food));
            }
            Set<Restaurant> childRestaurants = restaurantChainService.getAllChildRestaurants(menu.getRestaurant(), new HashSet<>());
            if (CollectionUtils.isNotEmpty(childRestaurants)) {
                childRestaurants.forEach(childRestaurant -> restaurantChainService.deleteMenuFromRestaurantChain(childRestaurant, menu));
            }
            menuRepository.delete(menu);
        }
    }

    @Override
    public void updateMenu(Menu menu, String oldName) throws BaseException {
        if (getMenuById(menu.getId()) == null) {
            throw new MenuNotExistException("The menu does not exist!");
        } else {
            Set<Restaurant> childRestaurants = restaurantChainService.getAllChildRestaurants(menu.getRestaurant(), new HashSet<>());
            if (CollectionUtils.isNotEmpty(childRestaurants)) {
                childRestaurants
                        .forEach(childRestaurant -> childRestaurant.getMenus().stream()
                                .filter(childRestaurantMenu -> childRestaurantMenu.getName().equals(oldName))
                                .forEach(childRestaurantMenu -> {
                                    childRestaurantMenu.setName(menu.getName());
                                    menuRepository.save(childRestaurantMenu);
                                }));
            }
            menuRepository.save(menu);
        }
    }

    @Override
    public Menu getMenuById(Long id) {
        Menu menu = menuRepository.findById(id).orElse(null);
        if (menu == null) {
            throw new MenuNotExistException("The menu does not exist!");
        }
        return menu;
    }

    @Override
    public MenuDto transformDtoFromMenu(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        return menuDto;
    }

    @Override
    public Menu transformMenuFromDto(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        return menu;
    }
}
