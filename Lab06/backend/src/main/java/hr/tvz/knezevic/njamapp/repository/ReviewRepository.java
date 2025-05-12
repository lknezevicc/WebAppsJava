package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByRestaurantId(Long restaurantId);

    @Query("""
        SELECT r.restaurant
        FROM Review r
        WHERE r.createdAt >= :from
        GROUP BY r.restaurant
        ORDER BY AVG(r.rating) DESC
        """)
    List<Restaurant> findTopRatedRestaurantsByCreatedAt(LocalDateTime from);
}
