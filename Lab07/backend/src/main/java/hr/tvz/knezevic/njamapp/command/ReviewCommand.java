package hr.tvz.knezevic.njamapp.command;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReviewCommand(
    @NotNull(message = "Title is required")
    String title,

    @NotNull(message = "Description is required")
    String description,

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be max 5")
    Integer rating,

    Long restaurantId
) { }
