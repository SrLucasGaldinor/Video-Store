package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotBlank;

public class GenreUpdateDTO {
	
	@NotBlank(message = "Name is required")
	private String name;

	public GenreUpdateDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
