package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.MovieCopyStatus;

public class MovieCopyResponseDTO {

	private Long id;
	private MovieCopyStatus status;
	private Long movieId;

	public MovieCopyResponseDTO(Long id, MovieCopyStatus status, Long movieId) {
		this.id = id;
		this.status = status;
		this.movieId = movieId;
	}

	public Long getId() {
		return id;
	}

	public MovieCopyStatus getStatus() {
		return status;
	}

	public Long getMovieId() {
		return movieId;
	}
}
