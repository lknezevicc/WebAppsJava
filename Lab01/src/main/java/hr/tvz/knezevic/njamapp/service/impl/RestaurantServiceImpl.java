package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;
import hr.tvz.knezevic.njamapp.mappers.RestaurantMapper;
import hr.tvz.knezevic.njamapp.repository.RestaurantRepository;
import hr.tvz.knezevic.njamapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public RestaurantDTO findById(Long id) {
        return restaurantRepository.findById(id)
                .map(RestaurantMapper::toRestaurantDTO)
                .orElse(null);
    }

    @Override
    public List<RestaurantDTO> findNearest(String address) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getAddress().toLowerCase().contains(address.toLowerCase()))
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
    public List<RestaurantDTO> findByAddressAndMark(String address, Double mark) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getAddress().toLowerCase().contains(address.toLowerCase()))
                .filter(restaurant -> restaurant.getAverageCustomerRating() >= mark)
                .map(RestaurantMapper::toRestaurantDTO)
                .toList();
    }
}
