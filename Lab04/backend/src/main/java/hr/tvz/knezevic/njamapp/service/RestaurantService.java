package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;
import hr.tvz.knezevic.njamapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
    Optional<RestaurantDTO> findById(Long id);
    Optional<RestaurantDTO> findByName(String name);
    List<RestaurantDTO> findNearest(String address);
    List<RestaurantDTO> findBest(Double mark);
    Optional<RestaurantDTO> addRestaurant(RestaurantCommand restaurantCommand);
    boolean deleteRestaurant(Long id);
}
