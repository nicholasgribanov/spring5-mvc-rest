package name.nicholasgribanov.controllers.v1;

import name.nicholasgribanov.api.v1.model.CustomerDTO;
import name.nicholasgribanov.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {
    private final String FIRST_NAME = "Nikolay";
    private final String LAST_NAME = "Gribanov";
    private final String URL = "/customers/nikolya";

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Roman");
        customerDTO.setLastName("Varlamov");
        customerDTO.setCustomerUrl("/customer/roman");

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRST_NAME);
        customerDTO1.setLastName(LAST_NAME);
        customerDTO1.setCustomerUrl(URL);

        List<CustomerDTO> customerDTOS = Arrays.asList(customerDTO, customerDTO1);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerByFirstName() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(FIRST_NAME, LAST_NAME, URL);

        when(customerService.getCustomerByName(anyString())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/nikolay").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));

    }
}