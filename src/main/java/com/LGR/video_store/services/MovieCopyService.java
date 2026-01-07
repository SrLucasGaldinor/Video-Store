package com.LGR.video_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.MovieCopyCreateDTO;
import com.LGR.video_store.dtos.MovieCopyResponseDTO;
import com.LGR.video_store.entities.Movie;
import com.LGR.video_store.entities.MovieCopy;
import com.LGR.video_store.enums.MovieCopyStatus;
import com.LGR.video_store.exceptions.BusinessRuleException;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.MovieCopyRepository;
import com.LGR.video_store.repositories.MovieRepository;

@Service
public class MovieCopyService {

	private final MovieCopyRepository movieCopyRepository;
	private final MovieRepository movieRepository;

	public MovieCopyService(MovieCopyRepository movieCopyRepository, MovieRepository movieRepository) {
		this.movieCopyRepository = movieCopyRepository;
		this.movieRepository = movieRepository;
	}

	@Transactional
	public MovieCopyResponseDTO create(MovieCopyCreateDTO dto) {
		Movie movie = movieRepository.findByIdAndActiveTrue(dto.getMovieId())
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

		MovieCopy copy = new MovieCopy(movie);

		return toResponseDTO(movieCopyRepository.save(copy));
	}

	@Transactional(readOnly = true)
	public List<MovieCopyResponseDTO> findAll() {
		return movieCopyRepository.findByActiveTrue()
								  .stream()
								  .map(this::toResponseDTO)
								  .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public MovieCopyResponseDTO findById(Long id) {
		MovieCopy copy = movieCopyRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		return toResponseDTO(copy);
	}
	
	@Transactional
	public MovieCopyResponseDTO rentCopy(Long id) {
		MovieCopy copy = movieCopyRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		if(copy.getStatus() == MovieCopyStatus.AVAILABLE) {
			copy.markAsRented();
		} else {
			throw new BusinessRuleException("Movie copy is not available for rental");
		}
		
		return toResponseDTO(movieCopyRepository.save(copy));
	}
	
	@Transactional
	public void delete(Long id) {
		MovieCopy copy = movieCopyRepository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		copy.deactivate();
		movieCopyRepository.save(copy);
	}
	
	private MovieCopyResponseDTO toResponseDTO(MovieCopy copy) {
		return new MovieCopyResponseDTO(copy.getId(),
										copy.getStatus(),
										copy.getMovie().getId());
	}
}
