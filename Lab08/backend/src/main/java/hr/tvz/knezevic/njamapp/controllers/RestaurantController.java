package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> findAll() {
        return ResponseEntity.ok(restaurantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailsDTO> findById(@PathVariable Long id) {
        return restaurantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RestaurantDetailsDTO> findByName(@PathVariable String name) {
        return restaurantService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> addRestaurant(@Valid @RequestBody RestaurantCommand restaurantCommand) {
        return restaurantService.addRestaurant(restaurantCommand)
                .map(restaurant -> ResponseEntity.status(HttpStatus.CREATED).body(restaurant))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDetailsDTO> updateRestaurant(@PathVariable Long id, @Valid @RequestBody RestaurantCommand restaurantCommand) {
        return restaurantService.updateRestaurant(id, restaurantCommand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

}
