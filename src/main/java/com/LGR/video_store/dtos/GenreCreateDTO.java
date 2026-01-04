package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotBlank;

public class GenreCreateDTO {

	@NotBlank(message = "Name is required")
	private String name;
	
	public GenreCreateDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
