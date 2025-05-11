package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByRestaurantId(Long restaurantId);
}
