package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.model.Review;
import hr.tvz.knezevic.njamapp.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByRestaurantId(Long restaurantId);
    Optional<Review> findByUserAndRestaurant(UserInfo user, Restaurant restaurant);
}
