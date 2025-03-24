package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);
}
