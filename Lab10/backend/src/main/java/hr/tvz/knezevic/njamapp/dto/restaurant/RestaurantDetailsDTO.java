package hr.tvz.knezevic.njamapp.dto.restaurant;

import hr.tvz.knezevic.njamapp.dto.WorkingHourDTO;

import java.util.List;

public record RestaurantDetailsDTO(
        Long id,
        String name,
        String address,
        String phoneNumber,
        String email,
        List<WorkingHourDTO> workingHours,
        String description,
        Boolean opened,
        Double averageDeliveryTime,
        Double averageCustomerRating,
        Integer maxNumberOfOrders,
        Integer michelinStars
) { }
