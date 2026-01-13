package com.LGR.video_store.dtos;

import java.time.LocalDateTime;

import com.LGR.video_store.enums.PaymentStatus;
import com.LGR.video_store.enums.PaymentType;

public class PaymentResponseDTO {

	private Long id;
	private PaymentType paymentType;
	private PaymentStatus status;
	private Double amount;
	private LocalDateTime paymentDate;
	private Long rentalId;
		
	public PaymentResponseDTO(Long id, PaymentType paymentType, PaymentStatus status,
							  Double amount, LocalDateTime paymentDate, Long rentalId) {
		this.id = id;
		this.paymentType = paymentType;
		this.status = status;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.rentalId = rentalId;
	}

	public Long getId() {
		return id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public Long getRentalId() {
		return rentalId;
	}
}
