package hr.tvz.knezevic.njamapp.repository.impl;


import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import hr.tvz.knezevic.njamapp.enums.DayOfWeek;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    @PostConstruct
    private void initializeData() {
        restaurants.add(new Restaurant(1L, "Pizza House", "Zagrebačka 10", "099111222", "pizza@house.com",
                Map.of(DayOfWeek.MONDAY, "10:00 - 23:00",
                    DayOfWeek.TUESDAY, "10:00 - 23:00",
                    DayOfWeek.WEDNESDAY, "10:00 - 23:00",
                    DayOfWeek.THURSDAY, "10:00 - 23:00",
                    DayOfWeek.FRIDAY, "10:00 - 00:00",
                    DayOfWeek.SATURDAY, "12:00 - 00:00",
                    DayOfWeek.SUNDAY, "12:00 - 22:00"),
                "Pizza and more", true, 30.0, 4.5, 50, 2));

        restaurants.add(new Restaurant(2L, "Burger King", "Ilica 23", "099333444", "burger@king.com",
                Map.of(DayOfWeek.MONDAY, "09:00 - 22:00",
                    DayOfWeek.TUESDAY, "09:00 - 22:00",
                    DayOfWeek.WEDNESDAY, "09:00 - 22:00",
                    DayOfWeek.THURSDAY, "09:00 - 22:00",
                    DayOfWeek.FRIDAY, "09:00 - 23:00",
                    DayOfWeek.SATURDAY, "11:00 - 23:00",
                    DayOfWeek.SUNDAY, "11:00 - 21:00"),
                "Fast food with a twist", false, 25.0, 4.2, 40, 1));

        restaurants.add(new Restaurant(3L, "Sushi Bar", "Savska 50", "099555666", "sushi@bar.com",
                Map.of(DayOfWeek.MONDAY, "12:00 - 22:00",
                    DayOfWeek.TUESDAY, "12:00 - 22:00",
                    DayOfWeek.WEDNESDAY, "12:00 - 22:00",
                    DayOfWeek.THURSDAY, "12:00 - 22:00",
                    DayOfWeek.FRIDAY, "12:00 - 23:00",
                    DayOfWeek.SATURDAY, "13:00 - 23:00",
                    DayOfWeek.SUNDAY, "13:00 - 20:00"),
            "Fresh and tasty sushi", true, 40.0, 4.8, 30, 3));

        restaurants.add(new Restaurant(4L, "BBQ Joint", "Vukovarska 15", "099777888", "bbq@joint.com",
                Map.of(DayOfWeek.MONDAY, "11:00 - 22:00",
                    DayOfWeek.TUESDAY, "11:00 - 22:00",
                    DayOfWeek.WEDNESDAY, "11:00 - 22:00",
                    DayOfWeek.THURSDAY, "11:00 - 22:00",
                    DayOfWeek.FRIDAY, "11:00 - 23:00",
                    DayOfWeek.SATURDAY, "12:00 - 23:00",
                    DayOfWeek.SUNDAY, "12:00 - 21:00"),
            "BBQ delights", true, 35.0, 4.6, 45, 2));

        restaurants.add(new Restaurant(5L, "Healthy Bites", "Gajeva 3", "099999000", "healthy@bites.com",
                Map.of(DayOfWeek.MONDAY, "08:00 - 20:00",
                    DayOfWeek.TUESDAY, "08:00 - 20:00",
                    DayOfWeek.WEDNESDAY, "08:00 - 20:00",
                    DayOfWeek.THURSDAY, "08:00 - 20:00",
                    DayOfWeek.FRIDAY, "08:00 - 21:00",
                    DayOfWeek.SATURDAY, "10:00 - 21:00",
                    DayOfWeek.SUNDAY, "10:00 - 19:00"),
            "Nutritious meals", false, 20.0, 4.9, 25, 1));
    }

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

    @Override
    public Optional<Restaurant> findByName(String name) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        int index = restaurants.indexOf(restaurant);
        restaurants.set(index, restaurant);
    }

    @Override
    public boolean deleteById(Long id) {
        return restaurants.removeIf(restaurant -> restaurant.getId().equals(id));
    }
}
