package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.dto.RestaurantDTO;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
    RestaurantDTO findById(Long id);
    List<RestaurantDTO> findNearest(String address);
    List<RestaurantDTO> findBest(Double mark);
    List<RestaurantDTO> findByAddressAndMark(String address, Double mark);
}
