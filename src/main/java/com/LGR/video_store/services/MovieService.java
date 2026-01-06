package com.LGR.video_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.GenreResponseDTO;
import com.LGR.video_store.dtos.MovieCreateDTO;
import com.LGR.video_store.dtos.MoviePatchDTO;
import com.LGR.video_store.dtos.MovieResponseDTO;
import com.LGR.video_store.dtos.MovieUpdateDTO;
import com.LGR.video_store.entities.Genre;
import com.LGR.video_store.entities.Movie;
import com.LGR.video_store.exceptions.BusinessRuleException;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.GenreRepository;
import com.LGR.video_store.repositories.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository movieRepository;
	private final GenreRepository genreRepository;
	
	public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
		this.movieRepository = movieRepository;
		this.genreRepository = genreRepository;
	}
	
	@Transactional
	public MovieResponseDTO create(MovieCreateDTO dto) {
		
		List<Genre> genres = fetchAndValidateGenre(dto.getGenresId());
		
		Movie movie = new Movie();
		movie.setTitle(dto.getTitle());
		movie.setDirector(dto.getDirector());
		movie.setReleaseYear(dto.getReleaseYear());
		movie.setDurationMinutes(dto.getDurationMinutes());
		movie.setPosterURL(dto.getPosterURL());
		movie.getGenres().addAll(genres);
		
		return toResponseDTO(movieRepository.save(movie));
	}
	
	@Transactional(readOnly = true)
	public List<MovieResponseDTO> findAll() {
		return movieRepository.findByActiveTrue()
						 .stream()
						 .map(this::toResponseDTO)
						 .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public MovieResponseDTO findById(Long id) {
		Movie movie = movieRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
				
		return toResponseDTO(movie);		
	}
	
	@Transactional
	public MovieResponseDTO update(Long id, MovieUpdateDTO dto) {
		Movie movie = movieRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		List<Genre> genres = fetchAndValidateGenre(dto.getGenresId());
		
		movie.setTitle(dto.getTitle());
		movie.setDirector(dto.getDirector());
		movie.setReleaseYear(dto.getReleaseYear());
		movie.setDurationMinutes(dto.getDurationMinutes());
		movie.setPosterURL(dto.getPosterURL());
		
		movie.getGenres().clear();
		movie.getGenres().addAll(genres);
		
		return toResponseDTO(movieRepository.save(movie));
	}
	
	@Transactional
	public MovieResponseDTO patch(Long id, MoviePatchDTO dto) {
		Movie movie = movieRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		if(dto.getTitle() != null) {
			movie.setTitle(dto.getTitle());
		}
		
		if(dto.getDirector() != null) {
			movie.setDirector(dto.getDirector());
		}
		
		if(dto.getReleaseYear() != null) {
			movie.setReleaseYear(dto.getReleaseYear());
		}
		
		if(dto.getDurationMinutes() != null) {
			movie.setDurationMinutes(dto.getDurationMinutes());
		}
		
		if(dto.getPosterURL() != null) {
			movie.setPosterURL(dto.getPosterURL());
		}
		
		if(dto.getGenresId() != null) {
			List<Genre> genres = fetchAndValidateGenre(dto.getGenresId());
			movie.getGenres().clear();
			movie.getGenres().addAll(genres);
		}
		
		return toResponseDTO(movieRepository.save(movie));
	}
	
	public List<Genre> fetchAndValidateGenre(List<Long> genreIds) {
		
		if(genreIds == null || genreIds.isEmpty()) {
			throw new BusinessRuleException("Movie must have at least one genre");
		}
		
		List<Genre> genres = genreRepository.findAllById(genreIds);
		
		if(genres.size() != genreIds.size()) {
			throw new ResourceNotFoundException("One or more genres not found");
		}
		
		boolean hasInactiveGenre = genres.stream().anyMatch(genre -> !genre.isActive());
		
		if(hasInactiveGenre) {
			throw new BusinessRuleException("Inactive genres can't be associated with a movie");
		}
		return genres;
	}
	 
	@Transactional
	public void delete(Long id) {
		Movie movie = movieRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		movie.deactivate();
		movieRepository.save(movie);
	}
	
	private MovieResponseDTO toResponseDTO(Movie movie) {
		List<GenreResponseDTO> genres = movie.getGenres()
											 .stream()
											 .map(genre -> new GenreResponseDTO(genre.getId(), genre.getName()))
											 .collect(Collectors.toList());
		
		return new MovieResponseDTO(movie.getId(),
									movie.getTitle(),
									movie.getDirector(),
									movie.getReleaseYear(),
									movie.getDurationMinutes(),
									movie.getPosterURL(),
									genres);
	}
}
