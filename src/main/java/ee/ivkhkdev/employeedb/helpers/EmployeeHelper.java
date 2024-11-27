package ee.ivkhkdev.employeedb.helpers;

import ee.ivkhkdev.employeedb.entity.Employee;
import ee.ivkhkdev.employeedb.input.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeHelper implements Helper<Employee>{
    @Autowired
    Input input;

    /**
     * Создает объект Employee и инициирует его при помощи диалога с пользователем
     * @return Employee employee // инициированный объект
     */
    @Override
    public Optional<Employee> create() {
        try {
            System.out.println("=== Создание работника ===");
            Employee employee = new Employee("John", "Doe", "Developer", "5000");
            System.out.print("Имя: ");
            employee.setFirstname(input.nextLine());
            System.out.print("Фамилия: ");
            employee.setLastname(input.nextLine());
            System.out.print("Должность: ");
            employee.setPosition(input.nextLine());
            System.out.print("Зарплата: ");
            employee.setSalary(input.nextLine());
            return Optional.of(employee);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public boolean printList(List<Employee> employees) {
        if(employees.isEmpty()){
            System.out.println("Список работников пуст");
            return false;
        }
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.printf("%d. %s %s. %s. %s%n",
                    i+1,
                    employee.getFirstname(),
                    employee.getLastname(),
                    employee.getPosition(),
                    employee.getSalary()
            );
        }
        return true;
    }
}
