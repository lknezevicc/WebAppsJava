package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.dto.CustomerDTO;
import hr.tvz.knezevic.njamapp.model.Customer;

public class CustomerMapper {
    public static CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }
}
