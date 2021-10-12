package tr.com.foodstore.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.RestaurantDto;
import tr.com.foodstore.restaurant.service.RestaurantService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurants")
    public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantDto restaurantDto) {
        restaurantService.createRestaurant(restaurantService.transformRestaurantFromDto(restaurantDto));
        return ResponseEntity.ok().body("The restaurant created successfully!");
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        return ResponseEntity.ok().body(restaurantService.getAllRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok().body(restaurantService.transformDtoFromRestaurant(restaurantService.getRestaurantById(id)));
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantService.transformRestaurantFromDto(restaurantDto);
        restaurant.setId(id);
        restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok().body("The restaurant updated successfully!");
    }

    @DeleteMapping("/restaurants/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.ok().body("The restaurant updated successfully!");
    }

}
