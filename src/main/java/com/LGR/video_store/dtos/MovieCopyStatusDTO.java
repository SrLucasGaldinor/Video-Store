package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.MovieCopyStatus;

import jakarta.validation.constraints.NotBlank;

public class MovieCopyStatusDTO {

	@NotBlank(message = "Status can't be blank")
	private MovieCopyStatus status;

	public MovieCopyStatus getStatus() {
		return status;
	}
}
