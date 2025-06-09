package hr.tvz.knezevic.njamapp.configuration;

import hr.tvz.knezevic.njamapp.scheduler.OpenRestaurantsJob;
import hr.tvz.knezevic.njamapp.scheduler.TopCheapestRestaurants;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(OpenRestaurantsJob.class)
                .withIdentity("openRestaurantsJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("openRestaurantsTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }


    @Bean
    public JobDetail topCheapestRestaurantsJobDetail() {
        return JobBuilder.newJob(TopCheapestRestaurants.class)
                .withIdentity("topCheapestRestaurants")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger topCheapestRestaurantsTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(topCheapestRestaurantsJobDetail())
                .withIdentity("topCheapestRestaurantsTrigger")
                .startAt(new Date(System.currentTimeMillis() + 12_000))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(0))
                .build();
    }
}
