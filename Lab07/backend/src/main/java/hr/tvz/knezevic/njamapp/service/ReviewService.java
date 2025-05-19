package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByRestaurantId(Long restaurantId);
    Optional<ReviewDTO> addReview(ReviewCommand reviewCommand);
    Optional<ReviewDTO> updateReview(Long id, ReviewCommand reviewCommand);
    void delete(Long id);
}
