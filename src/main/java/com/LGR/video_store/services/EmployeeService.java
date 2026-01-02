package com.LGR.video_store.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.EmployeeCreateDTO;
import com.LGR.video_store.dtos.EmployeePatchDTO;
import com.LGR.video_store.dtos.EmployeeResponseDTO;
import com.LGR.video_store.dtos.EmployeeUpdateDTO;
import com.LGR.video_store.entities.Employee;
import com.LGR.video_store.entities.User;
import com.LGR.video_store.exceptions.BusinessRuleException;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.EmployeeRepository;
import com.LGR.video_store.repositories.UserRepository;

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
		User user = usrRepository.findByIdAndActiveTrue(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + dto.getUserId()));
		
		Optional<Employee> existingEmployee = empRepository.findByUser(user);
		
		if(existingEmployee.isPresent() && existingEmployee.get().isActive()) {
			throw new BusinessRuleException("The user is already linked to an active employee");
		}
		
		if(existingEmployee.isPresent() && !existingEmployee.get().isActive()) {
			Employee employee = existingEmployee.get();
			employee.setName(dto.getName());
			employee.setCpf(dto.getCpf());
			employee.activate();
			
			return toResponseDTO(empRepository.save(employee));
		}
		
		Employee employee = new Employee(dto.getName(), dto.getCpf(), user);
		return toResponseDTO(empRepository.save(employee));
	}
	
	@Transactional(readOnly = true)
	public List<EmployeeResponseDTO> findAll() {
		return empRepository.findByActiveTrue()
							.stream()
							.map(this::toResponseDTO)
							.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public EmployeeResponseDTO findById(Long id) {
		Employee employee = empRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		
		return toResponseDTO(employee);
	}
	
	@Transactional
	public EmployeeResponseDTO update(Long id, EmployeeUpdateDTO dto) {
		Employee employee = empRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new BusinessRuleException("Inactive or non-existent employee"));
		
		employee.setName(dto.getName());
		employee.setCpf(dto.getCpf());
		
		return toResponseDTO(empRepository.save(employee));
	}
	
	@Transactional
	public EmployeeResponseDTO patch(Long id, EmployeePatchDTO dto) {
		Employee employee = empRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new BusinessRuleException("Inactive or non-existent employee"));
		
		if(dto.getName() != null) {
			employee.setName(dto.getName());
		}
		if(dto.getCpf() != null) {
			employee.setCpf(dto.getCpf());
		}
		
		return toResponseDTO(empRepository.save(employee));
	}
	
	@Transactional
	public void delete(Long id) {
		Employee employee = empRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		
		employee.deactivate();
		empRepository.save(employee);
	}
	
	private EmployeeResponseDTO toResponseDTO(Employee employee) {
		return new EmployeeResponseDTO(employee.getId(),
									   employee.getName(),
									   employee.getCpf(),
									   employee.getUser().getId());
	}
}
