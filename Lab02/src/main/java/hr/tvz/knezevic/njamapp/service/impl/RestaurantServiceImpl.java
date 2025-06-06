package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;
import hr.tvz.knezevic.njamapp.mappers.RestaurantMapper;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll().stream()
                .map(RestaurantMapper::toRestaurantDTO)
                .toList();
    }

    @Override
    public Optional<RestaurantDTO> findById(Long id) {
        return restaurantRepository.findById(id)
                .map(RestaurantMapper::toRestaurantDTO);
    }

    @Override
    public Optional<RestaurantDTO> findByName(String name) {
        return restaurantRepository.findByName(name)
                .map(RestaurantMapper::toRestaurantDTO);
    }

    @Override
    public List<RestaurantDTO> findNearest(String address) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getAddress().equalsIgnoreCase(address))
                .map(RestaurantMapper::toRestaurantDTO)
                .toList();
    }

    @Override
    public List<RestaurantDTO> findBest(Double mark) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getAverageCustomerRating() >= mark)
                .map(RestaurantMapper::toRestaurantDTO)
                .toList();
    }

    @Override
    public Optional<RestaurantDTO> addRestaurant(RestaurantCommand restaurantCommand) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        boolean duplicateExists = restaurants.stream()
                .anyMatch(restaurant ->
                        restaurant.getName().equalsIgnoreCase(restaurantCommand.getName()) &&
                        restaurant.getAddress().equalsIgnoreCase(restaurantCommand.getAddress()));

        if (duplicateExists) return Optional.empty();

        Long newId = restaurants.stream()
                .map(Restaurant::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        Restaurant restaurant = new Restaurant(
                newId,
                restaurantCommand.getName(),
                restaurantCommand.getAddress(),
                restaurantCommand.getPhoneNumber(),
                restaurantCommand.getEmail(),
                restaurantCommand.getWorkingHours(),
                restaurantCommand.getDescription(),
                restaurantCommand.getOpened(),
                restaurantCommand.getAverageDeliveryTime(),
                restaurantCommand.getAverageCustomerRating(),
                restaurantCommand.getMaxNumberOfOrders(),
                restaurantCommand.getMichelinStars()
        );

        restaurantRepository.save(restaurant);
        return Optional.of(RestaurantMapper.toRestaurantDTO(restaurant));
    }

    @Override
    public boolean deleteRestaurant(Long id) {
        return restaurantRepository.deleteById(id);
    }

}
