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
@Profile("prod")
public class InMemoryRestaurantRepository implements RestaurantRepository {
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
                    false, 25.0, 4.2, 40),

            new Restaurant(3L, "Sushi Bar", "Savska 50", "099 555 666", "sushi@bar.com",
                    Map.of(DayOfWeek.MONDAY, "12:00 - 22:00",
                            DayOfWeek.TUESDAY, "12:00 - 22:00",
                            DayOfWeek.WEDNESDAY, "12:00 - 22:00",
                            DayOfWeek.THURSDAY, "12:00 - 22:00",
                            DayOfWeek.FRIDAY, "12:00 - 23:00",
                            DayOfWeek.SATURDAY, "13:00 - 23:00",
                            DayOfWeek.SUNDAY, "13:00 - 20:00"),
                    true, 40.0, 4.8, 30),

            new Restaurant(4L, "BBQ Joint", "Vukovarska 15", "099 777 888", "bbq@joint.com",
                    Map.of(DayOfWeek.MONDAY, "11:00 - 22:00",
                            DayOfWeek.TUESDAY, "11:00 - 22:00",
                            DayOfWeek.WEDNESDAY, "11:00 - 22:00",
                            DayOfWeek.THURSDAY, "11:00 - 22:00",
                            DayOfWeek.FRIDAY, "11:00 - 23:00",
                            DayOfWeek.SATURDAY, "12:00 - 23:00",
                            DayOfWeek.SUNDAY, "12:00 - 21:00"),
                    true, 35.0, 4.6, 45),

            new Restaurant(5L, "Healthy Bites", "Gajeva 3", "099 999 000", "healthy@bites.com",
                    Map.of(DayOfWeek.MONDAY, "08:00 - 20:00",
                            DayOfWeek.TUESDAY, "08:00 - 20:00",
                            DayOfWeek.WEDNESDAY, "08:00 - 20:00",
                            DayOfWeek.THURSDAY, "08:00 - 20:00",
                            DayOfWeek.FRIDAY, "08:00 - 21:00",
                            DayOfWeek.SATURDAY, "10:00 - 21:00",
                            DayOfWeek.SUNDAY, "10:00 - 19:00"),
                    false, 20.0, 4.9, 25)
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
