package com.LGR.video_store.dtos;

public class MovieResponseDTO {
	
	private Long id;
	private String title;
	private String director;
	private Integer release_year;
	private Integer duration_minutes;
	private String posterURL;
	
	public MovieResponseDTO(Long id, String title, String director, Integer release_year, Integer duration_minutes,
			String posterURL) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.release_year = release_year;
		this.duration_minutes = duration_minutes;
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

	public Integer getRelease_year() {
		return release_year;
	}

	public Integer getDuration_minutes() {
		return duration_minutes;
	}

	public String getPosterURL() {
		return posterURL;
	}
}
