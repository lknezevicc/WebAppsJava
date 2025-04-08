package hr.tvz.knezevic.njamapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Map<DayOfWeek, String> workingHours;
    private String description;
    private Boolean opened;
    private Double averageDeliveryTime;
    private Double averageCustomerRating;
    private Integer maxNumberOfOrders;
    private Integer michelinStars;
}
