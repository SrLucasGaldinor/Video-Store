package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotBlank;

public class MovieCopyCreateDTO {

	@NotBlank(message = "Movie ID is required")
	private Long movieId;
	
	public MovieCopyCreateDTO(Long movieId) {
		this.movieId = movieId;
	}

	public Long getMovieId() {
		return movieId;
	}
}
