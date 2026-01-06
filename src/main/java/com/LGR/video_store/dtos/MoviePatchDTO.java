package com.LGR.video_store.dtos;

import java.util.List;

import jakarta.validation.constraints.Size;

public class MoviePatchDTO {

	@Size(min = 3, message = "Title must have 3 characters")
	private String title;
	
	@Size(min = 3, message = "Director must have 3 characters")
	private String director;
	
	@Size(min = 4, max = 4, message = "Release year must contain 4 numbers")
	private Integer releaseYear;
	
	@Size(max = 3, message = "Duration in minutes should contain a maximum of 3 numbers")
	private Integer durationMinutes;
	
	@Size(min = 3, message = "Poster URL must have 3 characters")
	private String posterURL;
	
	@Size(min = 1, message = "Genre must have 1 characters")
	private List<Long> genresId;
	
	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public String getPosterURL() {
		return posterURL;
	}

	public List<Long> getGenresId() {
		return genresId;
	}	
}
