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

import com.LGR.video_store.dtos.MovieCreateDTO;
import com.LGR.video_store.dtos.MoviePatchDTO;
import com.LGR.video_store.dtos.MovieResponseDTO;
import com.LGR.video_store.dtos.MovieUpdateDTO;
import com.LGR.video_store.services.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private final MovieService service;
	
	public MovieController(MovieService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<MovieResponseDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<MovieResponseDTO> create(@Valid @RequestBody MovieCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> update(@PathVariable Long id,
												   @Valid @RequestBody MovieUpdateDTO dto) {
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<MovieResponseDTO> patch(@PathVariable Long id,
												  @Valid @RequestBody MoviePatchDTO dto) {
		return ResponseEntity.ok(service.patch(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
