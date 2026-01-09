package com.LGR.video_store.dtos;

import java.time.LocalDateTime;

import com.LGR.video_store.enums.PaymentType;

public class PaymentResponseDTO {

	private Long id;
	private PaymentType paymentType;
	private Double amount;
	private LocalDateTime paymentDate;
		
	public PaymentResponseDTO(Long id, PaymentType paymentType, Double amount, LocalDateTime paymentDate) {
		this.id = id;
		this.paymentType = paymentType;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}
	
	public Long getId() {
		return id;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public Double getAmount() {
		return amount;
	}
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
}
