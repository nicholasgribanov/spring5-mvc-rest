package name.nicholasgribanov.bootstrap;

import name.nicholasgribanov.domain.Category;
import name.nicholasgribanov.domain.Customer;
import name.nicholasgribanov.repositories.CategoryRepository;
import name.nicholasgribanov.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        Customer customer = new Customer();
        customer.setFirstName("Nikolay");
        customer.setLastName("Gribanov");
        customer.setCustomerUrl("/customer/nikolay");

        Customer roman = new Customer();
        roman.setFirstName("Roman");
        roman.setLastName("Varlamov");
        roman.setCustomerUrl("/customer/roman");

        customerRepository.save(customer);
        customerRepository.save(roman);


        System.out.println("Added count: " + categoryRepository.count());
        System.out.println("Added count customers: " + customerRepository.count());

    }
}
