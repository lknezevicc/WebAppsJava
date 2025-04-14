package hr.tvz.knezevic.njamapp.dto;

public record RestaurantDTO(
        Long id,
        String name,
        String address,
        Boolean opened,
        Double workloadPercentage
) {}
