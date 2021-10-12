package tr.com.foodstore.restaurant.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.foodstore.restaurant.model.domain.Menu;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.MenuDto;
import tr.com.foodstore.restaurant.service.MenuService;
import tr.com.foodstore.restaurant.service.RestaurantService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurants/{restaurantId}/menus")
    public ResponseEntity<String> createMenu(@RequestBody MenuDto menuDto, @PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Menu menu = menuService.transformMenuFromDto(menuDto);
        menu.setRestaurant(restaurant);
        restaurant.getMenus().add(menu);
        menuService.createMenu(menu);
        return ResponseEntity.ok().body("The menu created successfully!");
    }

    @GetMapping("/restaurants/{restaurantId}/menus")
    public ResponseEntity<Set<MenuDto>> getRestaurantMenus(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Set<MenuDto> menus = new HashSet<>();
        if (CollectionUtils.isNotEmpty(restaurant.getMenus())) {
            restaurant.getMenus().forEach(menu -> menus.add(menuService.transformDtoFromMenu(menu)));
        }
        return ResponseEntity.ok().body(menus);
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long id) {
        return ResponseEntity.ok().body(menuService.transformDtoFromMenu(menuService.getMenuById(id)));
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<String> updateMenu(@RequestBody MenuDto menuDto, @PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        String oldName = menu.getName();
        menu.setName(menuDto.getName().trim().toUpperCase());
        menuService.updateMenu(menu, oldName);
        return ResponseEntity.ok().body("The menu created successfully!");
    }

    @DeleteMapping("/menus/{id}")
    public ResponseEntity<String> deleteMenu(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        menuService.deleteMenu(menu);
        return ResponseEntity.ok().body("The menu created successfully!");
    }
}
