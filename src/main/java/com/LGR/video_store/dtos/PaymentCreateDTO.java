package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotNull;

public class PaymentCreateDTO {

	@NotNull(message = "Rental ID is required")
	private Long rentalId;
	
	@NotNull(message = "Amount is required")
	private Double amount;

	public PaymentCreateDTO(Long rentalId, Double amount) {
		this.rentalId = rentalId;
		this.amount = amount;
	}
	
	public Long getRentalId() {
		return rentalId;
	}

	public Double getAmount() {
		return amount;
	}
}
