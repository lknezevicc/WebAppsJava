package hr.tvz.knezevic.njamapp.dto.restaurant;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;

import java.util.Map;

public record RestaurantDetailsDTO(
        Long id,
        String name,
        String address,
        String phoneNumber,
        String email,
        Map<DayOfWeek, String>workingHours,
        String description,
        Boolean opened,
        Double averageDeliveryTime,
        Double averageCustomerRating,
        Integer maxNumberOfOrders,
        Integer michelinStars
) { }
