package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
import hr.tvz.knezevic.njamapp.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> findAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<ReviewDTO>> findAllByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(reviewService.findAllByRestaurantId(restaurantId));
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable Long restaurantId, @RequestBody @Valid final ReviewCommand reviewCommand) {
        return reviewService.addReview(restaurantId, reviewCommand)
                .map(review -> ResponseEntity.status(HttpStatus.CREATED).body(review))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/restaurant/top-rated-last-month")
    public ResponseEntity<RestaurantDetailsDTO> findTopRatedLastMonth() {
        return ResponseEntity.ok(reviewService.findTopRatedRestaurantsByCreatedAt(LocalDateTime.now().minusMonths(1)));
    }

    @GetMapping("/restaurant/top-rated-last-week")
    public ResponseEntity<RestaurantDetailsDTO> findTopRatedLastWeek() {
        return ResponseEntity.ok(reviewService.findTopRatedRestaurantsByCreatedAt(LocalDateTime.now().minusWeeks(1)));
    }
}
