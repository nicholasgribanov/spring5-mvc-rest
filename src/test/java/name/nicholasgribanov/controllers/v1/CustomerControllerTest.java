package name.nicholasgribanov.controllers.v1;

import name.nicholasgribanov.api.v1.model.CustomerDTO;
import name.nicholasgribanov.controllers.RestResponseEntityExceptionHandler;
import name.nicholasgribanov.services.CustomerService;
import name.nicholasgribanov.services.ResourceNotFoundException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest {
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
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
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

   /* @Test
    public void getCustomerByFirstName() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(FIRST_NAME, LAST_NAME, URL);

        when(customerService.getCustomerByName(anyString())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/nikolay").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));

    }*/

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Kostia");
        customerDTO.setLastName("Vaschenko");

        CustomerDTO customerDTOReturned = new CustomerDTO();
        customerDTOReturned.setFirstName(customerDTO.getFirstName());
        customerDTOReturned.setLastName(customerDTO.getLastName());
        customerDTOReturned.setCustomerUrl("/api/v1/customers/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(customerDTOReturned);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Kostia")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Kostia");
        customerDTO.setLastName("Vaschenko");

        CustomerDTO customerDTOReturned = new CustomerDTO();
        customerDTOReturned.setFirstName(customerDTO.getFirstName());
        customerDTOReturned.setLastName(customerDTO.getLastName());
        customerDTOReturned.setCustomerUrl("/api/v1/customers/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTOReturned);

        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Kostia")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/1")));
    }

    @Test
    public void patchCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Kostia");
        customerDTO.setLastName("Vaschenko");

        CustomerDTO customerDTOReturned = new CustomerDTO();
        customerDTOReturned.setFirstName(customerDTO.getFirstName());
        customerDTOReturned.setLastName("Varlamov");
        customerDTOReturned.setCustomerUrl("/api/v1/customers/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTOReturned);

        mockMvc.perform(patch("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Kostia")))
                .andExpect(jsonPath("$.lastName", equalTo("Varlamov")));
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void notFoundException() throws Exception {
        when(customerService.findCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}