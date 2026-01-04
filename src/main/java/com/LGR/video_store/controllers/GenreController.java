package com.LGR.video_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LGR.video_store.dtos.GenreCreateDTO;
import com.LGR.video_store.dtos.GenreResponseDTO;
import com.LGR.video_store.dtos.GenreUpdateDTO;
import com.LGR.video_store.services.GenreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {

	private final GenreService service;
	
	public GenreController(GenreService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<GenreResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GenreResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<GenreResponseDTO> create(@Valid @RequestBody GenreCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GenreResponseDTO> update(@PathVariable Long id,
												   @Valid @RequestBody GenreUpdateDTO dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
