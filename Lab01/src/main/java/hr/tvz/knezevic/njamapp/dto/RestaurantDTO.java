package hr.tvz.knezevic.njamapp.dto;

public record RestaurantDTO(
        Long id,
        String name,
        String address,
        boolean opened,
        Double workloadPercentage
) {}
