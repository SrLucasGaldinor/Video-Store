package com.LGR.video_store.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.LGR.video_store.enums.RentalStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_rental")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RentalStatus status;
	
	private LocalDateTime rentalDate;
	private LocalDateTime expectedReturnDate;
	private LocalDateTime returnDate;
	private Double totalValue;
	private Double fine;
	private boolean active = true;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@OneToMany(mappedBy = "rental", orphanRemoval = true, cascade = CascadeType.ALL)
	List<RentalItem> items = new ArrayList<>();
	
	@OneToOne(mappedBy = "rental")
	private Payment payment;
	
	public Rental() {
		this.rentalDate = LocalDateTime.now();
		this.status = RentalStatus.OPEN;
		this.fine = 0.0;
		this.totalValue = 0.0;
	}

	public Long getId() {
		return id;
	}
	
	public RentalStatus getStatus() {
		return status;
	}

	public void setStatus(RentalStatus status) {
		this.status = status;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Double getFine() {
		return fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public LocalDateTime getRentalDate() {
		return rentalDate;
	}

	public LocalDateTime getExpectedReturnDate() {
		return expectedReturnDate;
	}
	
	public void setExpectedReturnDate(LocalDateTime expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}

	public Double getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<RentalItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public boolean isActive() {
		return active;
	}
	
	public void activate() {
		this.active = true;
	}
	
	public void deactivate() {
		this.active = false;
	}
	
	public void calculateTotal() {
		this.totalValue = items.stream()
							   .filter(RentalItem::isActive)
							   .mapToDouble(RentalItem::calculateSubTotal)
							   .sum();
	}
	
	public void addItem(RentalItem item) {
		if(item == null) {
			throw new IllegalArgumentException("Rental Item can't be null");
		}
		
		item.setRental(this);
		this.items.add(item);
		calculateTotal();
	}
	
	public void removeItem(RentalItem item) {
		if(item == null) {
			throw new IllegalArgumentException("Rental Item can't be null");
		}
		
		if(this.items.remove(item)) {
			item.setRental(null);
			calculateTotal();
		}
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
		Rental other = (Rental) obj;
		return Objects.equals(id, other.id);
	}
}
