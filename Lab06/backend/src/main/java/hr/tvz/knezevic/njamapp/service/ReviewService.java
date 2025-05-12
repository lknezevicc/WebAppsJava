package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> findAll();
    Optional<ReviewDTO> addReview(Long restaurantId, ReviewCommand reviewCommand);
    List<ReviewDTO> findAllByRestaurantId(Long restaurantId);
    RestaurantDetailsDTO findTopRatedRestaurantsByCreatedAt(LocalDateTime from);
}
