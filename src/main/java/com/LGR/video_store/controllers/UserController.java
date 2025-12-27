package com.LGR.video_store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LGR.video_store.entities.User;
import com.LGR.video_store.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User user) {		
			return ResponseEntity.status(HttpStatus.CREATED).body(service.create(user));			
	}
	
}
