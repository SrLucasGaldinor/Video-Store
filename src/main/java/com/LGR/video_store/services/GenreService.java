package com.LGR.video_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.GenreCreateDTO;
import com.LGR.video_store.dtos.GenreResponseDTO;
import com.LGR.video_store.dtos.GenreUpdateDTO;
import com.LGR.video_store.entities.Genre;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.GenreRepository;

@Service
public class GenreService {

	private final GenreRepository repository;
	
	public GenreService(GenreRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public GenreResponseDTO create(GenreCreateDTO dto) {
		Genre genre = new Genre();
		genre.setName(dto.getName());
		
		return toResponseDTO(repository.save(genre));
	}
	
	@Transactional(readOnly = true)
	public List<GenreResponseDTO> findAll() {
		return repository.findByActiveTrue()
						 .stream()
						 .map(this::toResponseDTO)
						 .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public GenreResponseDTO findById(Long id) {
		Genre genre = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		return toResponseDTO(repository.save(genre));
	}
	
	@Transactional
	public GenreResponseDTO update(Long id, GenreUpdateDTO dto) {
		Genre genre = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		genre.setName(dto.getName());
		
		return toResponseDTO(repository.save(genre));
	}
	
	@Transactional
	public void delete(Long id) {
		Genre genre = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		genre.deactivate();
		repository.save(genre);
	}

	private GenreResponseDTO toResponseDTO(Genre genre) {
		return new GenreResponseDTO(genre.getId(),
									genre.getName());
	}
}
