package ee.ivkhkdev.employeedb;

import ee.ivkhkdev.employeedb.entity.Employee;
import ee.ivkhkdev.employeedb.helpers.EmployeeHelper;
import ee.ivkhkdev.employeedb.input.Input;
import ee.ivkhkdev.employeedb.services.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class EmployeeDbApplication implements CommandLineRunner {
	@Autowired
	private Input input;

	@Autowired
	private AppService<Employee> employeeService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean repeat = true;
		do {
			System.out.println("Список задач:");
			System.out.println("0. Выйти из программы");
			System.out.println("1. Добавить работника в список");
			System.out.println("2. Cписок работников");
			System.out.print("Выберите номер задачи: ");
			int task = Integer.parseInt(input.nextLine());
			switch (task) {
				case 0:
					repeat = false;
					break;
				case 1:
					if(employeeService.add()){
						System.out.println("Работник добавлен");
					}else{
						System.out.println("Работника добавить не удалось");
					}
					break;
				case 2:
					if(employeeService.print()){
						System.out.println("Список");
					}else{
						System.out.println("Списка нет");
					}
					break;
				default:
					System.out.println("Такого номера задачи в списке нет!");
			}
		}while(repeat);
		System.out.println("До свидания");
	}
}
