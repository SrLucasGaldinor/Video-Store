package com.LGR.video_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.MovieCreateDTO;
import com.LGR.video_store.dtos.MoviePatchDTO;
import com.LGR.video_store.dtos.MovieResponseDTO;
import com.LGR.video_store.dtos.MovieUpdateDTO;
import com.LGR.video_store.entities.Movie;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.MovieRepository;

@Service
public class MovieService {

	private final MovieRepository repository;
	
	public MovieService(MovieRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public MovieResponseDTO create(MovieCreateDTO dto) {
		Movie movie = new Movie();
		movie.setTitle(dto.getTitle());
		movie.setDirector(dto.getDirector());
		movie.setReleaseYear(dto.getReleaseYear());
		movie.setDurationMinutes(dto.getDurationMinutes());
		movie.setPosterURL(dto.getPosterURL());
		
		return toResponseDTO(repository.save(movie));
	}
	
	@Transactional(readOnly = true)
	public List<MovieResponseDTO> findAll() {
		return repository.findByActiveTrue()
						 .stream()
						 .map(this::toResponseDTO)
						 .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public MovieResponseDTO findById(Long id) {
		Movie movie = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
				
		return toResponseDTO(repository.save(movie));		
	}
	
	@Transactional
	public MovieResponseDTO update(Long id, MovieUpdateDTO dto) {
		Movie movie = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		movie.setTitle(dto.getTitle());
		movie.setDirector(dto.getDirector());
		movie.setReleaseYear(dto.getReleaseYear());
		movie.setDurationMinutes(dto.getDurationMinutes());
		movie.setPosterURL(dto.getPosterURL());
		
		return toResponseDTO(repository.save(movie));
	}
	
	@Transactional
	public MovieResponseDTO patch(Long id, MoviePatchDTO dto) {
		Movie movie = repository.findByIdAndActiveTrue(id)
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
		
		return toResponseDTO(repository.save(movie));
	}
	
	@Transactional
	public void delete(Long id) {
		Movie movie = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		movie.deactivate();
		repository.save(movie);
	}
	
	private MovieResponseDTO toResponseDTO(Movie movie) {
		return new MovieResponseDTO(movie.getId(),
									movie.getTitle(),
									movie.getDirector(),
									movie.getReleaseYear(),
									movie.getDurationMinutes(),
									movie.getPosterURL());
	}
}
