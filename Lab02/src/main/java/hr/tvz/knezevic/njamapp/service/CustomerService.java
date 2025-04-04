package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.command.CustomerCommand;
import hr.tvz.knezevic.njamapp.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> findAll();
    Optional<CustomerDTO> findById(Long id);
    Optional<CustomerDTO> addCustomer(CustomerCommand customerCommand);
    boolean deleteCustomer(Long id);
}
