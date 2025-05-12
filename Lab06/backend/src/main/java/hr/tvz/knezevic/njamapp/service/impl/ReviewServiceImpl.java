package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
import hr.tvz.knezevic.njamapp.mappers.RestaurantMapper;
import hr.tvz.knezevic.njamapp.mappers.ReviewMapper;
import hr.tvz.knezevic.njamapp.model.Review;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import hr.tvz.knezevic.njamapp.repository.ReviewRepository;
import hr.tvz.knezevic.njamapp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ReviewMapper reviewMapper,
                             RestaurantRepository restaurantRepository,
                             RestaurantMapper restaurantMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ReviewDTO> addReview(Long restaurantId, ReviewCommand reviewCommand) {
        return restaurantRepository.findById(restaurantId).map(restaurant -> {
            Review review = reviewMapper.toEntity(reviewCommand);
            review.setRestaurant(restaurant);
            Review saved = reviewRepository.save(review);
            return reviewMapper.toDto(saved);
        });
    }

    @Override
    public List<ReviewDTO> findAllByRestaurantId(Long restaurantId) {
        return reviewRepository.findAllByRestaurantId(restaurantId)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    @Override
    public RestaurantDetailsDTO findTopRatedRestaurantsByCreatedAt(LocalDateTime from) {
        return restaurantMapper.toDetailsDTO(reviewRepository.findTopRatedRestaurantsByCreatedAt(from)
                .getFirst());
    }
}
