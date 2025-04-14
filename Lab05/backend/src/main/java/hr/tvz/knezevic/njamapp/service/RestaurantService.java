package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
    Optional<RestaurantDetailsDTO> findById(Long id);
    Optional<RestaurantDetailsDTO> findByName(String name);
    List<RestaurantDTO> findNearest(String address);
    List<RestaurantDTO> findBest(Double mark);
    Optional<RestaurantDTO> addRestaurant(RestaurantCommand restaurantCommand);
    Optional<RestaurantDetailsDTO> updateRestaurant(Long id, RestaurantCommand restaurantCommand);
    boolean deleteRestaurant(Long id);
}
