package com.LGR.video_store.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.PaymentCreateDTO;
import com.LGR.video_store.dtos.PaymentProcessDTO;
import com.LGR.video_store.dtos.PaymentResponseDTO;
import com.LGR.video_store.entities.Payment;
import com.LGR.video_store.entities.Rental;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.PaymentRepository;
import com.LGR.video_store.repositories.RentalRepository;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final RentalRepository rentalRepository;
	
	public PaymentService(PaymentRepository paymentRepository, RentalRepository rentalRepository) {
		this.paymentRepository = paymentRepository;
		this.rentalRepository = rentalRepository;
	}
	
	@Transactional
	public PaymentResponseDTO create(PaymentCreateDTO dto) {
		Rental rental = rentalRepository.findById(dto.getRentalId())
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));	
		
		Payment payment = new Payment(rental, dto.getAmount());
		return toResponseDTO(paymentRepository.save(payment));
	}
	
	@Transactional
	public PaymentResponseDTO processPayment(Long id, PaymentProcessDTO dto) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		payment.payWith(dto.getPaymentType());
		
		return toResponseDTO(paymentRepository.save(payment));
	}

	private PaymentResponseDTO toResponseDTO(Payment payment) {
		return new PaymentResponseDTO(payment.getId(),
									  payment.getPaymentType(),
									  payment.getStatus(),
									  payment.getAmount(),
									  payment.getPaymentDate(),
									  payment.getRental().getId());
	}	
}
