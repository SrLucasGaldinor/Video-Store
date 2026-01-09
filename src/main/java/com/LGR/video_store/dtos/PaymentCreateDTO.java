package com.LGR.video_store.dtos;

import java.time.LocalDateTime;

import com.LGR.video_store.enums.PaymentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentCreateDTO {

	private PaymentType paymentType;
	
	@NotNull(message = "Amount is required")
	private Double amount;
	
	@NotBlank(message = "Date is required")
	private LocalDateTime paymentDate;

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
