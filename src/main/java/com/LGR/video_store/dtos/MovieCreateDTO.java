package com.LGR.video_store.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovieCreateDTO {

	@NotBlank(message = "Title is required")
	private String title;
	
	@NotBlank(message = "Director is required")
	private String director;
	
	@NotNull(message = "Release year is required")
	@Size(min = 4, max = 4, message = "Release year must contain 4 numbers")
	private Integer releaseYear;
	
	@NotNull(message = "Duration in minutes is required")
	@Size(max = 3, message = "Duration in minutes should contain a maximum of 3 numbers")
	private Integer durationMinutes;
	
	@NotBlank(message = "Poster URL is required")
	private String posterURL;
	
	@NotBlank(message = "Genre is required")
	private List<Long> genresId;
	
	public MovieCreateDTO(String title,
						  String director,
						  Integer releaseYear,
						  Integer durationMinutes,
						  String posterURL,
						  List<Long> genresId) {
		
		this.title = title;
		this.director = director;
		this.releaseYear = releaseYear;
		this.durationMinutes = durationMinutes;
		this.posterURL = posterURL;
		this.genresId = genresId;
	}

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
