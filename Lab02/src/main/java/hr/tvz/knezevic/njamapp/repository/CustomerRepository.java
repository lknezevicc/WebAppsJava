package hr.tvz.knezevic.njamapp.repository;

import hr.tvz.knezevic.njamapp.model.Customer;
import hr.tvz.knezevic.njamapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    void save(Customer customer);
    boolean deleteById(Long id);
}
