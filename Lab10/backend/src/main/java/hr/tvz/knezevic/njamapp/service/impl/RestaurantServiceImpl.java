package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.command.RestaurantCommand;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDTO;
import hr.tvz.knezevic.njamapp.dto.restaurant.RestaurantDetailsDTO;
import hr.tvz.knezevic.njamapp.mappers.RestaurantMapper;
import hr.tvz.knezevic.njamapp.model.Restaurant;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::toDto)
                .toList();
    }

    @Override
    public Optional<RestaurantDetailsDTO> findById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::toDetailsDTO);
    }

    @Override
    public Optional<RestaurantDetailsDTO> findByName(String name) {
        return restaurantRepository.findByName(name)
                .map(restaurantMapper::toDetailsDTO);
    }

    @Override
    public List<RestaurantDTO> findNearest(String address) {
        return restaurantRepository.findAllByAddressIgnoreCase(address)
                .stream()
                .map(restaurantMapper::toDto)
                .toList();
    }

    @Override
    public List<RestaurantDTO> findBest(Double mark) {
        return restaurantRepository.findAllByAverageCustomerRatingIsGreaterThan(mark)
                .stream()
                .map(restaurantMapper::toDto)
                .toList();
    }

    @Override
    public Optional<Restaurant> getById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Optional<RestaurantDTO> addRestaurant(RestaurantCommand restaurantCommand) {
        boolean duplicateExists = restaurantRepository.existsByNameIgnoreCaseAndAddressIgnoreCase(
                restaurantCommand.name(),
                restaurantCommand.address()
        );

        if (duplicateExists) return Optional.empty();

        Restaurant restaurant = restaurantMapper.toEntity(restaurantCommand);

        Restaurant result = restaurantRepository.save(restaurant);
        return Optional.ofNullable(restaurantMapper.toDto(result));
    }

    @Override
    public Optional<RestaurantDetailsDTO> updateRestaurant(Long id, RestaurantCommand restaurantCommand) {
        return restaurantRepository.findById(id).map(existing -> {
            restaurantMapper.updateEntity(restaurantCommand, existing);
            restaurantRepository.save(existing);
            return restaurantMapper.toDetailsDTO(existing);
        });
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public List<RestaurantDetailsDTO> findTop3Cheapest() {
        return restaurantRepository.findTop3Cheapest(PageRequest.of(0, 3))
                .stream()
                .map(restaurantMapper::toDetailsDTO)
                .toList();
    }

}
