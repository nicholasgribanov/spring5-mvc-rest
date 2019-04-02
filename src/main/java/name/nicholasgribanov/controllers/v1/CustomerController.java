package name.nicholasgribanov.controllers.v1;

import name.nicholasgribanov.api.v1.model.CustomerDTO;
import name.nicholasgribanov.api.v1.model.CustomerListDTO;
import name.nicholasgribanov.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {
        return new CustomerListDTO(customerService.getAllCustomers());
    }

    //TODO Implement get mapping for name and id
   /* @GetMapping("/{name}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String name) {
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerByName(name), HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerByFirstName(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
