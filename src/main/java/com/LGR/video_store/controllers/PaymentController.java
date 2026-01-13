package com.LGR.video_store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LGR.video_store.dtos.PaymentCreateDTO;
import com.LGR.video_store.dtos.PaymentProcessDTO;
import com.LGR.video_store.dtos.PaymentResponseDTO;
import com.LGR.video_store.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService service;
	
	public PaymentController(PaymentService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentCreateDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
	}
	
	@PatchMapping("/{id}/process")
	public ResponseEntity<PaymentResponseDTO> processPayment(@PathVariable Long id,
															 @Valid @RequestBody PaymentProcessDTO dto) {
		return ResponseEntity.ok(service.processPayment(id, dto));
	}
	
}
