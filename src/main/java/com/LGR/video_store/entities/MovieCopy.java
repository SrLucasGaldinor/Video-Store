package com.LGR.video_store.entities;

import java.util.Objects;

import com.LGR.video_store.enums.MovieCopyStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movie_copy")
public class MovieCopy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private MovieCopyStatus status;
	
	private boolean active = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id", nullable = false)
	private Movie movie;
	
	public MovieCopy() {

	}

	public MovieCopy(Movie movie) {
		this.movie = movie;
		this.status = MovieCopyStatus.AVAILABLE;
	}

	public Long getId() {
		return id;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public MovieCopyStatus getStatus() {
		return status;
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
	
	public void markAsAvailable() {
		this.status = MovieCopyStatus.AVAILABLE;
	}

	public void markAsRented() {
		this.status = MovieCopyStatus.RENTED;
	}
	
	public void markAsDamaged() {
		this.status = MovieCopyStatus.DAMAGED;
	}
	
	public void markAsLost() {
		this.status = MovieCopyStatus.LOST;
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
		MovieCopy other = (MovieCopy) obj;
		return Objects.equals(id, other.id);
	}
}
