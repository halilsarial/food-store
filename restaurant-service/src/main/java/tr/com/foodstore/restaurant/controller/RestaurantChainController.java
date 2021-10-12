package tr.com.foodstore.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.foodstore.restaurant.model.domain.Restaurant;
import tr.com.foodstore.restaurant.model.dto.RestaurantDto;
import tr.com.foodstore.restaurant.service.RestaurantChainService;
import tr.com.foodstore.restaurant.service.RestaurantService;

import java.util.HashSet;
import java.util.Set;

@RestController("/restaurants")
public class RestaurantChainController {

    @Autowired
    private RestaurantChainService restaurantChainService;

    @Autowired
    private RestaurantService restaurantService;

    @PutMapping("/restaurants/{restaurantId}/restaurantChains")
    public ResponseEntity<String> connectRestaurantToChain(@PathVariable Long restaurantId, @RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurantChain = restaurantService.getRestaurantById(restaurantId);
        Restaurant restaurant = restaurantService.transformRestaurantFromDto(restaurantDto);
        restaurantChainService.connectRestaurantToChain(restaurantChain, restaurant);
        return ResponseEntity.ok().body("The restaurant connected to the chain successfully!");
    }

    @GetMapping("/restaurants/{restaurantId}/restaurantChains")
    public ResponseEntity<Set<RestaurantDto>> getChildRestaurantsOfChain(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Set<Restaurant> allChildRestaurants = restaurantChainService.getAllChildRestaurants(restaurant, new HashSet<>());
        Set<RestaurantDto> restaurants = new HashSet<>();
        allChildRestaurants.forEach(r -> restaurants.add(restaurantService.transformDtoFromRestaurant(r)));
        return ResponseEntity.ok().body(restaurants);
    }

    @DeleteMapping("/restaurants/{restaurantId}/restaurantChains/{restaurantChainId}")
    public ResponseEntity<String> removeRestaurantFromChain(@PathVariable Long restaurantId, @PathVariable Long restaurantChainId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Restaurant restaurantChain = restaurantService.getRestaurantById(restaurantChainId);
        restaurantChainService.removeRestaurantFromChain(restaurantChain, restaurant);
        return ResponseEntity.ok().body("The restaurant removed from the chain successfully!");
    }
}
