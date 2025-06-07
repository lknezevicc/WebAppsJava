package hr.tvz.knezevic.njamapp.command;

import jakarta.validation.constraints.*;

import java.util.List;

public record RestaurantCommand(
    @NotBlank(message = "Name is mandatory")
    String name,

    @NotBlank(message = "Address is mandatory")
    String address,

    @Pattern(regexp = "^09[0-9]{6,8}$", message = "Phone number must be Croatian format")
    @NotBlank(message = "Phone number is required")
    String phoneNumber,

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    String email,

    @NotEmpty(message = "Working hours are required")
    List<WorkingHourCommand> workingHours,

    @NotBlank(message = "Description is required")
    String description,

    @NotNull(message = "Opened status is required")
    Boolean opened,

    @NotNull(message = "Average delivery time is required")
    @PositiveOrZero(message = "Average delivery time must be a positive number or zero")
    Double averageDeliveryTime,

    @NotNull(message = "Average customer rating is required")
    @Min(value = 0, message = "Average customer rating must be a non-negative number")
    @Max(value = 5, message = "Average customer rating must be between 0 and 5")
    Double averageCustomerRating,

    @NotNull(message = "Max number of orders is required")
    @PositiveOrZero(message = "Max number of orders must be a positive number or zero")
    Integer maxNumberOfOrders,

    @NotNull(message = "Michelin stars are required")
    @Min(value = 0, message = "Michelin stars must be a non-negative number")
    @Max(value = 3, message = "Michelin stars must be between 0 and 3")
    Integer michelinStars
) { }
