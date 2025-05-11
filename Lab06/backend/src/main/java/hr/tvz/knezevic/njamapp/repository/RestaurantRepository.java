package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
    boolean existsByNameIgnoreCaseAndAddressIgnoreCase(String name, String address);
    List<Restaurant> findAllByAddressIgnoreCase(String address);
    List<Restaurant> findAllByAverageCustomerRatingIsGreaterThan(double averageCustomerRating);
}
