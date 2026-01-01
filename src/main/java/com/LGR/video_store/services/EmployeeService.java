package com.LGR.video_store.services;

import org.springframework.stereotype.Service;

import com.LGR.video_store.dtos.EmployeeCreateDTO;
import com.LGR.video_store.dtos.EmployeeResponseDTO;
import com.LGR.video_store.entities.Employee;
import com.LGR.video_store.entities.User;
import com.LGR.video_store.exceptions.BusinessRuleException;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.EmployeeRepository;
import com.LGR.video_store.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	private final EmployeeRepository empRepository;
	private final UserRepository usrRepository;
	
	public EmployeeService(EmployeeRepository empRepository, UserRepository usrRepository) {
		this.empRepository = empRepository;
		this.usrRepository = usrRepository;
	}
	
	@Transactional
	public EmployeeResponseDTO create(EmployeeCreateDTO dto) {
		User user = usrRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + dto.getUserId()));
		
		if(empRepository.existsByUserId(dto.getUserId())) {
			throw new BusinessRuleException("The user is already linked to an employee");
		}
		
		Employee employee = new Employee();
		employee.setName(dto.getName());
		employee.setCpf(dto.getCpf());
		employee.setUser(user);
		
		Employee savedEmployee = empRepository.save(employee);
		
		return toResponseDTO(savedEmployee);
	}
	
	private EmployeeResponseDTO toResponseDTO(Employee employee) {
		return new EmployeeResponseDTO(employee.getId(),
									   employee.getName(),
									   employee.getCpf(),
									   employee.getUser().getId());
	}
}
