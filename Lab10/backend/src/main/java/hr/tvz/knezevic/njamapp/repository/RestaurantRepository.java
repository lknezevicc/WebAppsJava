package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
    boolean existsByNameIgnoreCaseAndAddressIgnoreCase(String name, String address);
    List<Restaurant> findAllByAddressIgnoreCase(String address);
    List<Restaurant> findAllByAverageCustomerRatingIsGreaterThan(double averageCustomerRating);

    @Query("SELECT r FROM Restaurant r ORDER BY r.id DESC")
    List<Restaurant> findTop3Cheapest(Pageable pageable);
}
