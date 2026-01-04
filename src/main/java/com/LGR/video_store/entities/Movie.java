package com.LGR.video_store.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String director;
	private Integer release_year;
	private Integer duration_minutes;
	private String posterURL;
	private boolean active = true;
	
	public Movie() {

	}

	public Movie(String title, String director, Integer release_year, Integer duration_minutes, String posterURL) {
		this.title = title;
		this.director = director;
		this.release_year = release_year;
		this.duration_minutes = duration_minutes;
		this.posterURL = posterURL;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Integer getRelease_year() {
		return release_year;
	}

	public void setRelease_year(Integer release_year) {
		this.release_year = release_year;
	}

	public Integer getDuration_minutes() {
		return duration_minutes;
	}

	public void setDuration_minutes(Integer duration_minutes) {
		this.duration_minutes = duration_minutes;
	}

	public String getPosterURL() {
		return posterURL;
	}

	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
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
		Movie other = (Movie) obj;
		return Objects.equals(id, other.id);
	}
}
