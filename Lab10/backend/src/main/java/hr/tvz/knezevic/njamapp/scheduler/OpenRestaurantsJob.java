package hr.tvz.knezevic.njamapp.scheduler;

import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenRestaurantsJob implements Job {

    private final RestaurantService restaurantService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("==== Opened Restaurants ====");
        restaurantService.findAll().stream()
                .filter(RestaurantDTO::opened)
                .forEach(System.out::println);
    }
}
