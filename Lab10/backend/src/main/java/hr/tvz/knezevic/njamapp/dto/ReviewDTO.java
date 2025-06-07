package hr.tvz.knezevic.njamapp.dto;

public record ReviewDTO(
        Long id,
        String title,
        String description,
        Integer rating,
        String username
) { }
