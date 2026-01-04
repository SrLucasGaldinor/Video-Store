package com.LGR.video_store.dtos;

public class GenreResponseDTO {

	private Long id;
	private String name;
	
	public GenreResponseDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
