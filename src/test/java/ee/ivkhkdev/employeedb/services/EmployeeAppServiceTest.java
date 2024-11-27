package ee.ivkhkdev.employeedb.services;

import ee.ivkhkdev.employeedb.entity.Employee;
import ee.ivkhkdev.employeedb.helpers.Helper;
import ee.ivkhkdev.employeedb.repositoryes.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeAppServiceTest {
    @Mock
    private Helper<Employee> employeeHelper;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeAppService employeeAppService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAdd_Success() {
        Employee mockEmployee = new Employee("John", "Doe", "Developer", "5000");
        when(employeeHelper.create()).thenReturn(Optional.of(mockEmployee));
        when(employeeRepository.save(mockEmployee)).thenReturn(mockEmployee);

        boolean result = employeeAppService.add();

        assertTrue(result, "The add method should return true on successful save.");
        verify(employeeHelper).create();
        verify(employeeRepository).save(mockEmployee);
    }
    @Test
    void testAdd_Failure_NoEmployeeCreated() {
        when(employeeHelper.create()).thenReturn(Optional.empty());

        boolean result = employeeAppService.add();

        assertFalse(result, "The add method should return false if no employee is created.");
        verify(employeeHelper).create();
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testAdd_Failure_ExceptionThrown() {
        when(employeeHelper.create()).thenThrow(new RuntimeException("Test exception"));

        boolean result = employeeAppService.add();

        assertFalse(result, "The add method should return false if an exception is thrown.");
        verify(employeeHelper).create();
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void testList() {
        List<Employee> result = employeeAppService.list();

        assertNotNull(result, "The list method should not return null.");
        assertTrue(result.isEmpty(), "The list method should return an empty list.");
    }

    @Test
    void testPrint_Success() {
        List<Employee> employees = List.of(new Employee("John", "Doe", "Developer", "5000"), new Employee("John", "Doe", "Developer", "5000"));
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeHelper.printList(employees)).thenReturn(true);

        boolean result = employeeAppService.print();

        assertTrue(result, "The print method should return true if printing succeeds.");
        verify(employeeRepository).findAll();
        verify(employeeHelper).printList(employees);
    }

    @Test
    void testPrint_Failure() {
        List<Employee> employees = List.of(new Employee("John", "Doe", "Developer", "5000"), new Employee("John", "Doe", "Developer", "5000"));
        when(employeeRepository.findAll()).thenReturn(employees);
        when(employeeHelper.printList(employees)).thenReturn(false);

        boolean result = employeeAppService.print();

        assertFalse(result, "The print method should return false if printing fails.");
        verify(employeeRepository).findAll();
        verify(employeeHelper).printList(employees);
    }


}