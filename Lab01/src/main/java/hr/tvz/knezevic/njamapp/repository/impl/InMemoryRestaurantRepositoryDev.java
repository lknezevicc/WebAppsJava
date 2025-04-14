package hr.tvz.knezevic.njamapp.repository.impl;

import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("dev")
public class InMemoryRestaurantRepositoryDev implements RestaurantRepository {
    private final List<Restaurant> restaurants = List.of(
            new Restaurant(1L, "Pizza House", "Zagrebačka 10", "099 111 222", "pizza@house.com",
                    Map.of(DayOfWeek.MONDAY, "10:00 - 23:00",
                            DayOfWeek.TUESDAY, "10:00 - 23:00",
                            DayOfWeek.WEDNESDAY, "10:00 - 23:00",
                            DayOfWeek.THURSDAY, "10:00 - 23:00",
                            DayOfWeek.FRIDAY, "10:00 - 00:00",
                            DayOfWeek.SATURDAY, "12:00 - 00:00",
                            DayOfWeek.SUNDAY, "12:00 - 22:00"),
                    true, 30.0, 4.5, 50),

            new Restaurant(2L, "Burger King", "Ilica 23", "099 333 444", "burger@king.com",
                    Map.of(DayOfWeek.MONDAY, "09:00 - 22:00",
                            DayOfWeek.TUESDAY, "09:00 - 22:00",
                            DayOfWeek.WEDNESDAY, "09:00 - 22:00",
                            DayOfWeek.THURSDAY, "09:00 - 22:00",
                            DayOfWeek.FRIDAY, "09:00 - 23:00",
                            DayOfWeek.SATURDAY, "11:00 - 23:00",
                            DayOfWeek.SUNDAY, "11:00 - 21:00"),
                    false, 25.0, 4.2, 40)
    );

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst();
    }
}
