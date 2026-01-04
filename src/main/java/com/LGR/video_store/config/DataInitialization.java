package com.LGR.video_store.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.LGR.video_store.dtos.ClientCreateDTO;
import com.LGR.video_store.dtos.EmployeeCreateDTO;
import com.LGR.video_store.dtos.UserCreateDTO;
import com.LGR.video_store.enums.Role;
import com.LGR.video_store.services.ClientService;
import com.LGR.video_store.services.EmployeeService;
import com.LGR.video_store.services.UserService;

@Component
@Profile("test")
public class DataInitialization implements CommandLineRunner {

	private final UserService usrService;
	private final EmployeeService empService;
	private final ClientService clientService;

	public DataInitialization(UserService usrService, EmployeeService empService, ClientService clientService) {
		this.usrService = usrService;
		this.empService = empService;
		this.clientService = clientService;
		
	}

	@Override
	public void run(String... args) {
		System.out.println(">>> DATA INITIALIZATION RUNNING <<<");
		createData();
	}

	private void createData() {

		UserCreateDTO user1 = new UserCreateDTO("admin", "admin123", Role.ADMIN);
		UserCreateDTO user2 = new UserCreateDTO("employee01", "password01", Role.EMPLOYEE);
		UserCreateDTO user3 = new UserCreateDTO("employee02", "password02", Role.EMPLOYEE);
		UserCreateDTO user4 = new UserCreateDTO("employee03", "password03", Role.EMPLOYEE);
		UserCreateDTO user5 = new UserCreateDTO("employee04", "password04", Role.EMPLOYEE);
		

		var savedUser1 = usrService.create(user1);
		var savedUser2 = usrService.create(user2);
		var savedUser3 = usrService.create(user3);
		var savedUser4 = usrService.create(user4);
		var savedUser5 = usrService.create(user5);

		EmployeeCreateDTO emp1 = new EmployeeCreateDTO("Admin Employee", "11111111111", savedUser1.getId());
		EmployeeCreateDTO emp2 = new EmployeeCreateDTO("Employee One", "22222222222", savedUser2.getId());
		EmployeeCreateDTO emp3 = new EmployeeCreateDTO("Employee Two", "33333333333", savedUser3.getId());
		EmployeeCreateDTO emp4 = new EmployeeCreateDTO("Employee Three", "44444444444", savedUser4.getId());
		EmployeeCreateDTO emp5 = new EmployeeCreateDTO("Employee Four", "55555555555", savedUser5.getId());

		empService.create(emp1);
		empService.create(emp2);
		empService.create(emp3);
		empService.create(emp4);
		empService.create(emp5);
		
		ClientCreateDTO client1 = new ClientCreateDTO("client01", "11111111111", "01111111111");
		ClientCreateDTO client2 = new ClientCreateDTO("client02", "22222222222", "02222222222");
		ClientCreateDTO client3 = new ClientCreateDTO("client03", "33333333333", "03333333333");
		ClientCreateDTO client4 = new ClientCreateDTO("client04", "44444444444", "04444444444");
		ClientCreateDTO client5 = new ClientCreateDTO("client05", "55555555555", "05555555555");
		
		clientService.create(client1);
		clientService.create(client2);
		clientService.create(client3);
		clientService.create(client4);
		clientService.create(client5);
	}
}
