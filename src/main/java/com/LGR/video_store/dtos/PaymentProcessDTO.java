package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.PaymentType;

import jakarta.validation.constraints.NotNull;

public class PaymentProcessDTO {

	@NotNull(message = "Payment type is required")
	private PaymentType paymentType;
	
	public PaymentType getPaymentType() {
		return paymentType;
	}
}
