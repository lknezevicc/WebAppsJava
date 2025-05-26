package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.enums.UserRole;
import hr.tvz.knezevic.njamapp.mappers.ReviewMapper;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.model.Review;
import hr.tvz.knezevic.njamapp.model.UserInfo;
import hr.tvz.knezevic.njamapp.repository.ReviewRepository;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import hr.tvz.knezevic.njamapp.service.ReviewService;
import hr.tvz.knezevic.njamapp.service.UserService;
import hr.tvz.knezevic.njamapp.util.SecurityUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    @Override
    public List<ReviewDTO> findAllByRestaurantId(Long restaurantId) {
        return reviewRepository.findAllByRestaurantId(restaurantId)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ReviewDTO> addReview(ReviewCommand reviewCommand) {
        String username = SecurityUtil.getCurrentUsername();

        UserInfo user = userService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Restaurant restaurant = restaurantService.getById(reviewCommand.restaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        if (reviewRepository.findByUserAndRestaurant(user, restaurant).isPresent()) {
            throw new IllegalStateException("User already submitted a review for this restaurant.");
        }

        Review review = reviewMapper.toEntity(reviewCommand);
        review.setUser(user);
        review.setRestaurant(restaurant);

        Review savedReview = reviewRepository.save(review);

        return Optional.ofNullable(reviewMapper.toDto(savedReview));
    }

    @Override
    public Optional<ReviewDTO> updateReview(Long id, ReviewCommand reviewCommand) {
        String username = SecurityUtil.getCurrentUsername();

        UserInfo user = userService.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return reviewRepository.findById(id).map(existing -> {
            boolean isAdmin = user.getUserGroups().stream()
                    .anyMatch(group -> group.getRole().equals(UserRole.ADMIN));

            if (!existing.getUser().getId().equals(user.getId()) && !isAdmin) {
                throw new AccessDeniedException("You are not allowed to edit this review.");
            }

            reviewMapper.updateEntity(reviewCommand, existing);
            return reviewMapper.toDto(reviewRepository.save(existing));
        });
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
