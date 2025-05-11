package hr.tvz.knezevic.njamapp.command;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantCommand {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Pattern(regexp = "^09[0-9]{6,8}$", message = "Phone number must be Croatian format")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotEmpty(message = "Working hours are required")
    private List<WorkingHourCommand> workingHours;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Opened status is required")
    private Boolean opened;

    @NotNull(message = "Average delivery time is required")
    @PositiveOrZero(message = "Average delivery time must be a positive number or zero")
    private Double averageDeliveryTime;

    @NotNull(message = "Average customer rating is required")
    @Min(value = 0, message = "Average customer rating must be a non-negative number")
    @Max(value = 5, message = "Average customer rating must be between 0 and 5")
    private Double averageCustomerRating;

    @NotNull(message = "Max number of orders is required")
    @PositiveOrZero(message = "Max number of orders must be a positive number or zero")
    private Integer maxNumberOfOrders;

    @NotNull(message = "Michelin stars are required")
    @Min(value = 0, message = "Michelin stars must be a non-negative number")
    @Max(value = 3, message = "Michelin stars must be between 0 and 3")
    private Integer michelinStars;
}
