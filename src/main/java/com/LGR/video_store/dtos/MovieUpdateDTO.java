package com.LGR.video_store.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovieUpdateDTO {

	@NotBlank(message = "Title can't be blank")
	private String title;
	
	@NotBlank(message = "Director can't be blank")
	private String director;
	
	@NotNull(message = "Release year can't be blank")
	@Size(min = 4, max = 4, message = "Release year must contain 4 numbers")
	private Integer releaseYear;
	
	@NotNull(message = "Duration in minutes can't be blank")
	@Size(max = 3, message = "Duration in minutes should contain a maximum of 3 numbers")
	private Integer durationMinutes;
	
	@NotBlank(message = "Poster URL can't be blank")
	private String posterURL;
	
	@NotBlank(message = "Genre can't be blank")
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
