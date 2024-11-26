package ee.ivkhkdev.employeedb.services;

import ee.ivkhkdev.employeedb.entity.Employee;
import ee.ivkhkdev.employeedb.helpers.EmployeeHelper;
import ee.ivkhkdev.employeedb.helpers.Helper;
import ee.ivkhkdev.employeedb.repositoryes.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeAppService implements AppService<Employee>{
    @Autowired
    private Helper<Employee> employeeHelper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean add() {
        try {
            Optional<Employee> employee = employeeHelper.create();
            if(employee.isPresent()){
                employeeRepository.save(employee.get());
                return true;
            }
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    @Override
    public List<Employee> list() {
        return List.of();
    }

    @Override
    public boolean print() {
        return employeeHelper.printList(employeeRepository.findAll());
    }
}
