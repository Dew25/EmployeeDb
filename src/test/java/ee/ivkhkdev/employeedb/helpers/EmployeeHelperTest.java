package ee.ivkhkdev.employeedb.helpers;

import ee.ivkhkdev.employeedb.entity.Employee;
import ee.ivkhkdev.employeedb.input.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeHelperTest {
    @Mock
    private Input input;

    private EmployeeHelper employeeHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeHelper = new EmployeeHelper();
        employeeHelper.input = input; // Внедрение мока вручную
    }

    @Test
    void testCreate_Success() {
        // Задаем поведение мока
        when(input.nextLine()).thenReturn("John", "Doe", "Developer", "5000");

        Optional<Employee> result = employeeHelper.create();

        assertTrue(result.isPresent(), "The create method should return a non-empty Optional when valid input is provided.");
        Employee employee = result.get();
        assertEquals("John", employee.getFirstname());
        assertEquals("Doe", employee.getLastname());
        assertEquals("Developer", employee.getPosition());
        assertEquals("5000", employee.getSalary());

        // Проверяем количество вызовов input.nextLine()
        verify(input, times(4)).nextLine();
    }

    @Test
    void testCreate_Failure_Exception() {
        // Задаем поведение мока с выбросом исключения
        when(input.nextLine()).thenThrow(new RuntimeException("Input error"));

        Optional<Employee> result = employeeHelper.create();

        assertFalse(result.isPresent(), "The create method should return an empty Optional when an exception occurs.");
        verify(input).nextLine(); // Проверяем, что метод был вызван хотя бы раз
    }

    @Test
    void testPrintList_Success() {
        // Создаем список сотрудников
        List<Employee> employees = List.of(
                new Employee("John", "Doe", "Developer", "5000"),
                new Employee("Jane", "Smith", "Manager", "6000")
        );

        boolean result = employeeHelper.printList(employees);

        assertTrue(result, "The printList method should return true when the list is not empty.");
    }

    @Test
    void testPrintList_EmptyList() {
        List<Employee> employees = List.of();

        boolean result = employeeHelper.printList(employees);

        assertFalse(result, "The printList method should return false when the list is empty.");
    }
}
