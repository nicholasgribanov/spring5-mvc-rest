package name.nicholasgribanov.services;

import name.nicholasgribanov.api.v1.mapper.CustomerMapper;
import name.nicholasgribanov.api.v1.model.CustomerDTO;
import name.nicholasgribanov.domain.Customer;
import name.nicholasgribanov.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {
    private final String FIRST_NAME = "Roman";
    private final String LAST_NAME = "Varlamov";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerByName() {
        Customer customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findByFirstName(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByName(FIRST_NAME);

        assertEquals(customerDTO.getFirstName(), FIRST_NAME);
        assertEquals(customerDTO.getLastName(), LAST_NAME);
    }
}