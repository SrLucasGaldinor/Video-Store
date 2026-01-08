package com.LGR.video_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LGR.video_store.dtos.MovieCopyCreateDTO;
import com.LGR.video_store.dtos.MovieCopyResponseDTO;
import com.LGR.video_store.services.MovieCopyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/copies")
public class MovieCopyController {

	private final MovieCopyService service;
	
	public MovieCopyController(MovieCopyService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<MovieCopyResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieCopyResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<MovieCopyResponseDTO> create(@Valid @RequestBody MovieCopyCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PatchMapping("/rent/{id}")
	public ResponseEntity<MovieCopyResponseDTO> rentCopy(@PathVariable Long id) {
		return ResponseEntity.ok(service.rentCopy(id));
	}
	
	@PatchMapping("/return/{id}")
	public ResponseEntity<MovieCopyResponseDTO> returnCopy(@PathVariable Long id) {
		return ResponseEntity.ok(service.returnCopy(id));
	}
	
	@PatchMapping("/damaged/{id}")
	public ResponseEntity<MovieCopyResponseDTO> markAsDamaged(@PathVariable Long id) {
		return ResponseEntity.ok(service.markAsDamaged(id));
	}
	
	@PatchMapping("/lost/{id}")
	public ResponseEntity<MovieCopyResponseDTO> markAsLost(@PathVariable Long id) {
		return ResponseEntity.ok(service.markAsLost(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
