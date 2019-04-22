package name.nicholasgribanov.api.v1.mapper;

import name.nicholasgribanov.api.v1.model.CustomerDTO;
import name.nicholasgribanov.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {
    public static final String NAME = "Roman";
    public static final String  LASTNAME = "Varlam";

    @Test
    public void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setFirstName(NAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);

        assertEquals(customerDTO.getFirstName(), NAME);
        assertEquals(customerDTO.getLastName(), LASTNAME);

    }
}