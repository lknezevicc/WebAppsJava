package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.command.CustomerCommand;
import hr.tvz.knezevic.njamapp.dto.CustomerDTO;
import hr.tvz.knezevic.njamapp.mappers.CustomerMapper;
import hr.tvz.knezevic.njamapp.model.Customer;
import hr.tvz.knezevic.njamapp.repository.CustomerRepository;
import hr.tvz.knezevic.njamapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toCustomerDTO)
                .toList();
    }

    @Override
    public Optional<CustomerDTO> findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper::toCustomerDTO);
    }

    @Override
    public Optional<CustomerDTO> addCustomer(CustomerCommand customerCommand) {
        List<Customer> customers = customerRepository.findAll();
        boolean duplicateExists = customers.stream()
                .anyMatch(customer -> customer.getEmail().equals(customerCommand.getEmail()));

        if (duplicateExists) return Optional.empty();

        Long newId = customers.stream()
                .map(Customer::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        Customer customer = new Customer(
                newId,
                customerCommand.getFirstName(),
                customerCommand.getLastName(),
                customerCommand.getEmail(),
                customerCommand.getPhoneNumber(),
                customerCommand.getAccountValidUntil()
        );

        customerRepository.save(customer);

        return Optional.of(CustomerMapper.toCustomerDTO(customer));
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
}
