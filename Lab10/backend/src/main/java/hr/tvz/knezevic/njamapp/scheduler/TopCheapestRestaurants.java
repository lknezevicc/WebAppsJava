package hr.tvz.knezevic.njamapp.scheduler;

import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
import hr.tvz.knezevic.njamapp.mappers.RestaurantMapper;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TopCheapestRestaurants implements Job {
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("==== Top 3 cheapest Restaurants ====");
        List<RestaurantDTO> restaurants = new ArrayList<>(restaurantService.findAll());
        Collections.shuffle(restaurants);

        restaurants.stream().limit(3).forEach(System.out::println);

        restaurants.forEach(restaurant -> {
            Optional<RestaurantDetailsDTO> restaurantDetailsDTO = restaurantService.findById(restaurant.id());
            Restaurant tempRestaurant = restaurantMapper.fromDetailsToEntity(restaurantDetailsDTO.get());

            tempRestaurant.setId(null);

            restaurantService.addRestaurant(restaurantMapper.toCommand(tempRestaurant));
        });
    }
}
