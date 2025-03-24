package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public RestaurantDTO findById(@PathVariable Long id) {
        return restaurantService.findById(id);
    }
}
