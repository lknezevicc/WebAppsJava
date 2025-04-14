package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;
import hr.tvz.knezevic.njamapp.model.Restaurant;

import java.util.Random;

public class RestaurantMapper {
    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        int numberOfOrders = new Random().nextInt(10, 101);
        Double workloadPercentage = (restaurant.getMaxNumberOfOrders() / (double) numberOfOrders) * 100;

        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.isOpened(),
                workloadPercentage
        );
    }
}
