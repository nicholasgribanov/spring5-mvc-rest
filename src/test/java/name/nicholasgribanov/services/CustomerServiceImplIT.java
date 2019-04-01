package name.nicholasgribanov.services;

import name.nicholasgribanov.api.v1.mapper.CustomerMapper;
import name.nicholasgribanov.api.v1.model.CustomerDTO;
import name.nicholasgribanov.bootstrap.Bootstrap;
import name.nicholasgribanov.domain.Customer;
import name.nicholasgribanov.repositories.CategoryRepository;
import name.nicholasgribanov.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void patchCustomerUpdatedFirstName() throws Exception {
        String updatedName = "Sergei";

        long id = getCustomerId();

        Customer originalCustomer = customerRepository.getOne(id);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
    }

    @Test
    public void patchCustomerUpdatedLastName() throws Exception {
        String updatedName = "Sergei";

        long id = getCustomerId();

        Customer originalCustomer = customerRepository.getOne(id);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
    }

    private long getCustomerId() {
        List<Customer> customers = customerRepository.findAll();

        return customers.get(0).getId();
    }
}
