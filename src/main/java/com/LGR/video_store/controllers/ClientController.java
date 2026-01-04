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

import com.LGR.video_store.dtos.ClientCreateDTO;
import com.LGR.video_store.dtos.ClientPatchDTO;
import com.LGR.video_store.dtos.ClientResponseDTO;
import com.LGR.video_store.dtos.ClientUpdateDTO;
import com.LGR.video_store.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService service;
	
	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<ClientResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id,
													@Valid @RequestBody ClientUpdateDTO dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ClientResponseDTO> patch(@PathVariable Long id,
												   @RequestBody ClientPatchDTO dto) {
		return ResponseEntity.ok(service.patch(id, dto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
