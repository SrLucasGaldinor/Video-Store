package com.LGR.video_store.dtos;

public class MovieResponseDTO {
	
	private Long id;
	private String title;
	private String director;
	private Integer releaseYear;
	private Integer durationMinutes;
	private String posterURL;
	
	public MovieResponseDTO(Long id, String title, String director, Integer releaseYear, Integer durationMinutes,
			String posterURL) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.releaseYear = releaseYear;
		this.durationMinutes = durationMinutes;
		this.posterURL = posterURL;
	}

	public Long getId() {
		return id;
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
}
