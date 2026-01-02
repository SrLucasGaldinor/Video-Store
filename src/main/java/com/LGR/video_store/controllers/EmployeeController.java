package com.LGR.video_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LGR.video_store.dtos.EmployeeCreateDTO;
import com.LGR.video_store.dtos.EmployeePatchDTO;
import com.LGR.video_store.dtos.EmployeeResponseDTO;
import com.LGR.video_store.dtos.EmployeeUpdateDTO;
import com.LGR.video_store.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id,
													  @Valid @RequestBody EmployeeUpdateDTO dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> patch(@PathVariable Long id,
													 @Valid @RequestBody EmployeePatchDTO dto) {
		return ResponseEntity.ok(service.patch(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
