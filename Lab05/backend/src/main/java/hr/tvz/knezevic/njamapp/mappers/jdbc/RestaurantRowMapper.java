package hr.tvz.knezevic.njamapp.mappers.jdbc;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RestaurantRowMapper implements RowMapper<Restaurant> {
    private final JdbcTemplate jdbcTemplate;

    public RestaurantRowMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long restaurantId = rs.getLong("id");
        Map<DayOfWeek, String> workingHours = getWorkingHoursForRestaurant(restaurantId);

        return Restaurant.builder()
                .id(restaurantId)
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .phoneNumber(rs.getString("phone_number"))
                .email(rs.getString("email"))
                .workingHours(workingHours)
                .description(rs.getString("description"))
                .opened(rs.getBoolean("is_opened"))
                .averageDeliveryTime(rs.getDouble("average_delivery_time"))
                .averageCustomerRating(rs.getDouble("average_customer_rating"))
                .maxNumberOfOrders(rs.getInt("max_number_of_orders"))
                .michelinStars(rs.getInt("michelin_stars"))
                .build();
    }

    private Map<DayOfWeek, String> getWorkingHoursForRestaurant(Long restaurantId) {
        Map<DayOfWeek, String> workingHoursMap = new HashMap<>();

        String sql = "SELECT * FROM working_hours WHERE restaurant_id = ?";
        jdbcTemplate.query(sql, new Object[]{restaurantId}, rs -> {
            String dayOfWeek = rs.getString("day_of_week").toUpperCase();
            DayOfWeek dayOfWeekEnum = DayOfWeek.valueOf(dayOfWeek);
            String hour = rs.getString("hours");

            workingHoursMap.put(dayOfWeekEnum, hour);
        });

        return workingHoursMap;
    }
}
