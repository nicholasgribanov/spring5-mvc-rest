package name.nicholasgribanov.services;

import name.nicholasgribanov.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerByName(String name);
}
