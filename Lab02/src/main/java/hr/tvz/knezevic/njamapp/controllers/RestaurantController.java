package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;
import hr.tvz.knezevic.njamapp.mappers.RestaurantMapper;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDTO> findAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable Long id) {
        return restaurantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<RestaurantDTO> findByName(@PathVariable String name) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        return restaurantService.deleteRestaurant(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
