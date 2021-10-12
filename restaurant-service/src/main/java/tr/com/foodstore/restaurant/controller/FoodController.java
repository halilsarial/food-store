package tr.com.foodstore.restaurant.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.foodstore.restaurant.model.domain.Food;
import tr.com.foodstore.restaurant.model.domain.Menu;
import tr.com.foodstore.restaurant.model.dto.FoodDto;
import tr.com.foodstore.restaurant.service.FoodService;
import tr.com.foodstore.restaurant.service.MenuService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/menus/{menuId}/foods")
    public ResponseEntity<String> createFood(@RequestBody FoodDto foodDto, @PathVariable Long menuId) {
        Menu menu = menuService.getMenuById(menuId);
        Food food = foodService.transformFoodFromDto(foodDto);
        food.setMenu(menu);
        foodService.createFood(food);
        return ResponseEntity.ok().body("The food created successfully!");
    }

    @GetMapping("/menus/{menuId}/foods")
    public ResponseEntity<Set<FoodDto>> getMenuFoods(@PathVariable Long menuId) {
        Menu menu = menuService.getMenuById(menuId);
        Set<FoodDto> foods = new HashSet<>();
        if (CollectionUtils.isNotEmpty(menu.getFoods())) {
            menu.getFoods().forEach(food -> foods.add(foodService.transformDtoFromFood(food)));
        }
        return ResponseEntity.ok().body(foods);
    }


    @GetMapping("/foods/{id}")
    public ResponseEntity<FoodDto> getFood(@PathVariable Long id) {
        return ResponseEntity.ok().body(foodService.transformDtoFromFood(foodService.getFoodById(id)));
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<String> updateFood(@RequestBody FoodDto foodDto, @PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        String oldName = food.getName();
        food.setName(foodDto.getName().trim().toUpperCase());
        food.setPrice(foodDto.getPrice());
        foodService.updateFood(food, oldName);
        return ResponseEntity.ok().body("The food updated successfully!");
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        foodService.deleteFood(food);
        return ResponseEntity.ok().body("The food created successfully!");
    }
}
