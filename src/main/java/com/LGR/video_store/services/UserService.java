package com.LGR.video_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.UserCreateDTO;
import com.LGR.video_store.dtos.UserPatchDTO;
import com.LGR.video_store.dtos.UserResponseDTO;
import com.LGR.video_store.dtos.UserUpdateDTO;
import com.LGR.video_store.entities.User;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.UserRepository;



@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public UserResponseDTO create(UserCreateDTO dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());

		User savedUser = repository.save(user);

		return toResponseDTO(savedUser);
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll() {
		return repository.findByActiveTrue()
						 .stream()	
						 .map(this::toResponseDTO)
						 .collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public UserResponseDTO findById(Long id) {
		User user = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + id));

		return toResponseDTO(user);
	}

	@Transactional
	public UserResponseDTO update(Long id, UserUpdateDTO dto) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + id));

		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());

		return toResponseDTO(repository.save(user));
	}

	@Transactional
	public UserResponseDTO patch(Long id, UserPatchDTO dto) {
		User user = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + id));

		if (dto.getUserName() != null) {
			user.setUserName(dto.getUserName());
		}

		if (dto.getPassword() != null) {
			user.setPassword(dto.getPassword());
		}

		if (dto.getRole() != null) {
			user.setRole(dto.getRole());
		}

		return toResponseDTO(repository.save(user));
	}

	@Transactional
	public void delete(Long id) {
		User user = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with Id: " + id));
		
		user.setActive(false);
		repository.save(user);
	}

	private UserResponseDTO toResponseDTO(User user) {
		return new UserResponseDTO(user.getId(), user.getUserName(), user.getRole());
	}
}