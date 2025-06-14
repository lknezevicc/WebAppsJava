package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.model.WorkingHour;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Random;

@Mapper(componentModel = "spring", uses = WorkingHourMapper.class)
public interface RestaurantMapper {

    @Mapping(target = "workloadPercentage", expression = "java(calculateWorkloadPercentage(restaurant))")
    RestaurantDTO toDto(Restaurant restaurant);
    RestaurantDetailsDTO toDetailsDTO(Restaurant restaurant);
    Restaurant toEntity(RestaurantCommand command);
    Restaurant fromDetailsToEntity(RestaurantDetailsDTO dto);
    RestaurantCommand toCommand(Restaurant restaurant);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "workingHours", target = "workingHours")
    void updateEntity(RestaurantCommand command, @MappingTarget Restaurant restaurant);

    default Double calculateWorkloadPercentage(Restaurant restaurant) {
        int numberOfOrders = new Random().nextInt(10, 101);
        return (restaurant.getMaxNumberOfOrders() / (double) numberOfOrders) * 100;
    }

    @AfterMapping
    default void linkWorkingHours(@MappingTarget Restaurant restaurant) {
        if (restaurant.getWorkingHours() != null) {
            List<WorkingHour> filtered = restaurant.getWorkingHours().stream()
                    .filter(wh -> wh.getWorkingHour() != null && !wh.getWorkingHour().isEmpty())
                    .toList();

            restaurant.setWorkingHours(filtered);

            filtered.forEach(wh -> wh.setRestaurant(restaurant));
        }
    }
}
