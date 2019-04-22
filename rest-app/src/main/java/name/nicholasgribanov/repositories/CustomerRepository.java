package name.nicholasgribanov.repositories;

import name.nicholasgribanov.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String firstName);
}
