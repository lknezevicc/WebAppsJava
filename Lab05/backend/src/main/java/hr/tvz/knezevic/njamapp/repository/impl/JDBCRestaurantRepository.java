package hr.tvz.knezevic.njamapp.repository.impl;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;
import hr.tvz.knezevic.njamapp.mappers.jdbc.RestaurantRowMapper;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Primary
public class JDBCRestaurantRepository implements RestaurantRepository {
    private final JdbcTemplate jdbcTemplate;

    public JDBCRestaurantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Restaurant> findAll() {
        String sql = "SELECT * FROM restaurants";
        return jdbcTemplate.query(sql, new RestaurantRowMapper(jdbcTemplate));
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        String sql = "SELECT * FROM restaurants WHERE id = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql, new RestaurantRowMapper(jdbcTemplate), id)
        );
    }

    @Override
    public Optional<Restaurant> findByName(String name) {
        String sql = "SELECT * FROM restaurants WHERE name = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(sql, new RestaurantRowMapper(jdbcTemplate), name)
        );
    }

    @Override
    public void save(Restaurant restaurant) {
        String sql = "INSERT INTO restaurants (name, address, phone_number, email, description, is_opened, average_delivery_time, average_customer_rating, max_number_of_orders, michelin_stars) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, restaurant.getName(), restaurant.getAddress(), restaurant.getPhoneNumber(),
                restaurant.getEmail(), restaurant.getDescription(), restaurant.getOpened(), restaurant.getAverageDeliveryTime(),
                restaurant.getAverageCustomerRating(), restaurant.getMaxNumberOfOrders(), restaurant.getMichelinStars());

        addWorkingHours(restaurant.getId(), restaurant.getWorkingHours());
    }

    private void addWorkingHours(Long restaurantId, Map<DayOfWeek, String> workingHours) {
        String sql = "INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES (?, ?, ?)";

        for (Map.Entry<DayOfWeek, String> entry : workingHours.entrySet()) {
            jdbcTemplate.update(sql, restaurantId, entry.getKey().name(), entry.getValue());
        }
    }

    @Override
    public void update(Restaurant restaurant) {
        String sql = "UPDATE restaurants SET name = ?, address = ?, phone_number = ?, email = ?, description = ?, " +
                "is_opened = ?, average_delivery_time = ?, average_customer_rating = ?, max_number_of_orders = ?, " +
                "michelin_stars = ? WHERE id = ?";

        jdbcTemplate.update(sql, restaurant.getName(), restaurant.getAddress(), restaurant.getPhoneNumber(),
                restaurant.getEmail(), restaurant.getDescription(), restaurant.getOpened(), restaurant.getAverageDeliveryTime(),
                restaurant.getAverageCustomerRating(), restaurant.getMaxNumberOfOrders(), restaurant.getMichelinStars(), restaurant.getId());

        updateWorkingHours(restaurant.getId(), restaurant.getWorkingHours());
    }

    private void updateWorkingHours(Long restaurantId, Map<DayOfWeek, String> workingHours) {
        String deleteSql = "DELETE FROM working_hours WHERE restaurant_id = ?";
        jdbcTemplate.update(deleteSql, restaurantId);

        String insertSql = "INSERT INTO working_hours (restaurant_id, day_of_week, hours) VALUES (?, ?, ?)";
        for (Map.Entry<DayOfWeek, String> entry : workingHours.entrySet()) {
            jdbcTemplate.update(insertSql, restaurantId, entry.getKey().name(), entry.getValue());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM restaurants WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
