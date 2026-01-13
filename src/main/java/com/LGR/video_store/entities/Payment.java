package com.LGR.video_store.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import com.LGR.video_store.enums.PaymentStatus;
import com.LGR.video_store.enums.PaymentType;
import com.LGR.video_store.exceptions.BusinessRuleException;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	private Double amount;
	private LocalDateTime paymentDate;
	
	@OneToOne
	@JoinColumn(name = "rental_id", nullable = false)
	private Rental rental;

	public Payment() {

	}

	public Payment(Rental rental, Double amount) {
		this.rental = rental;
		this.amount = amount;
		this.status = PaymentStatus.PENDING;
	}
	
	public void payWith(PaymentType type) {
		if(this.status == PaymentStatus.PAID) {
			throw new BusinessRuleException("Payment already completed");
		}
		
		this.paymentType = type;
		this.paymentDate = LocalDateTime.now();
		this.status = PaymentStatus.PAID;
	}
	
	public void cancel() {
		if(this.status == PaymentStatus.PAID) {
			throw new BusinessRuleException("Payment already completed");
		}
		
		this.status = PaymentStatus.CANCELED;
	}
	
	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	
	public Rental getRental() {
		return rental;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
}
