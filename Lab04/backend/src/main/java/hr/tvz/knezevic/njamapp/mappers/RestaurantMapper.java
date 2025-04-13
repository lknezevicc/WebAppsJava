package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
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
                restaurant.getOpened(),
                workloadPercentage
        );
    }

    public static RestaurantDetailsDTO toRestaurantDetailsDTO(Restaurant restaurant) {
        return new RestaurantDetailsDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhoneNumber(),
                restaurant.getEmail(),
                restaurant.getWorkingHours(),
                restaurant.getDescription(),
                restaurant.getOpened(),
                restaurant.getAverageDeliveryTime(),
                restaurant.getAverageCustomerRating(),
                restaurant.getMaxNumberOfOrders(),
                restaurant.getMichelinStars()
        );
    }
}
