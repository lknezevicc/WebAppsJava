package hr.tvz.knezevic.njamapp.repository.impl;

import hr.tvz.knezevic.njamapp.model.Customer;
import hr.tvz.knezevic.njamapp.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    @PostConstruct
    private void initializeData() {
        customers.add(new Customer(1L, "John", "Doe", "john.doe@example.com",
                "0952134321", LocalDate.of(2026, 12, 31)));

        customers.add(new Customer(2L, "Alice", "Smith", "alice.smith@example.com",
                "0987654321", LocalDate.of(2025, 6, 30)));

        customers.add(new Customer(3L, "Michael", "Brown", "michael.brown@example.com",
                "0917654321", LocalDate.of(2027, 1, 15)));

        customers.add(new Customer(4L, "Emma", "Johnson", "emma.johnson@example.com",
                "0997658820", LocalDate.of(2028, 5, 20)));

        customers.add(new Customer(5L, "Robert", "Williams", "robert.williams@example.com",
                "0927124987", LocalDate.of(2029, 9, 10)));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean deleteById(Long id) {
        return customers.removeIf(customer -> customer.getId().equals(id));
    }
}
