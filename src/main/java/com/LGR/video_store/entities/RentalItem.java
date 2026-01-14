package com.LGR.video_store.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_rental_item")
public class RentalItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "rental_id", nullable = false)
	private Rental rental;
	
	@ManyToOne
	@JoinColumn(name = "movie_copy_id", nullable = false)
	private MovieCopy movieCopy;
	
	private Double dailyRate;
	private Integer rentedDays;
	private boolean active = true;
	
	public RentalItem() {

	}
	
	public RentalItem(Rental rental, MovieCopy movieCopy, Double dailyRate, Integer rentedDays) {
		this.rental = rental;
		this.movieCopy = movieCopy;
		this.dailyRate = dailyRate;
		this.rentedDays = rentedDays;
	}

	public Long getId() {
		return id;
	}
	
	public Rental getRental() {
		return rental;
	}
	
	public void setRental(Rental rental) {
		this.rental = rental;
	}
	
	public MovieCopy getMovieCopy() {
		return movieCopy;
	}
	
	public void setMovieCopy(MovieCopy movieCopy) {
		this.movieCopy = movieCopy;
	}
	
	public Double getDailyRate() {
		return dailyRate;
	}
	
	public void setDailyRate(Double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	public Integer getRentedDays() {
		return rentedDays;
	}
	
	public void setRentedDays(Integer rentedDays) {
		this.rentedDays = rentedDays;
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
	
	public Double calculateSubTotal() {
		return dailyRate * rentedDays;
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
		RentalItem other = (RentalItem) obj;
		return Objects.equals(id, other.id);
	}
}
