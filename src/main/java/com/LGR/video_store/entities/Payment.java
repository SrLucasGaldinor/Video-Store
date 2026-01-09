package com.LGR.video_store.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import com.LGR.video_store.enums.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	
	private Double amount;
	private LocalDateTime paymentDate;
	
	public Payment() {

	}

	public Payment(PaymentType paymentType, Double amount, LocalDateTime paymentDate) {
		this.paymentType = paymentType;
		this.amount = amount;
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getId() {
		return id;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
	
	 public void payWithDebit() {
		 this.paymentType = PaymentType.DEBIT;
	 }
	 
	 public void payWithCredit() {
		 this.paymentType = PaymentType.CREDIT;
	 }
	 
	 public void payWithPIX() {
		 this.paymentType = PaymentType.PIX;
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
