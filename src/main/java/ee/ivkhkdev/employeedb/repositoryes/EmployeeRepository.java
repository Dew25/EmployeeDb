package ee.ivkhkdev.employeedb.repositoryes;

import ee.ivkhkdev.employeedb.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //Optional<Employee> findByLastname(String lastname);
}
