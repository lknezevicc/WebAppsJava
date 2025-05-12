package hr.tvz.knezevic.njamapp.dto;

import java.time.LocalDateTime;

public record ReviewDTO(
        Long id,
        String title,
        String description,
        Integer rating,
        LocalDateTime createdAt
) { }
