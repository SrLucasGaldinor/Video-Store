package com.LGR.video_store.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LGR.video_store.dtos.ClientCreateDTO;
import com.LGR.video_store.dtos.ClientPatchDTO;
import com.LGR.video_store.dtos.ClientResponseDTO;
import com.LGR.video_store.dtos.ClientUpdateDTO;
import com.LGR.video_store.entities.Client;
import com.LGR.video_store.exceptions.ResourceNotFoundException;
import com.LGR.video_store.repositories.ClientRepository;

@Service
public class ClientService {
	
	private final ClientRepository repository;
	
	public ClientService(ClientRepository repository) {
		this.repository = repository;
	}
	
	@Transactional
	public ClientResponseDTO create(ClientCreateDTO dto) {
		Client client = new Client();
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setPhone(dto.getPhone());
		
		return toResponseDTO(repository.save(client));
	}

	@Transactional(readOnly = true)
	public List<ClientResponseDTO> findAll() {
		return repository.findByActiveTrue()
								  .stream()
								  .map(this::toResponseDTO)
								  .collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ClientResponseDTO findById(Long id) {
		Client client = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		return toResponseDTO(client);
	}
	
	@Transactional
	public ClientResponseDTO update(Long id, ClientUpdateDTO dto) {
		Client client = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setPhone(dto.getPhone());
		
		return toResponseDTO(repository.save(client));
	}
	
	@Transactional
	public ClientResponseDTO patch(Long id, ClientPatchDTO dto) {
		Client client = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		if(dto.getName() != null) {
			client.setName(dto.getName());
		}
		
		if(dto.getCpf() != null) {
			client.setCpf(dto.getCpf());
		}
		
		if(dto.getPhone() != null) {
			client.setPhone(dto.getPhone());
		}
		
		return toResponseDTO(repository.save(client));
	}
	
	@Transactional
	public void delete(Long id) {
		Client client = repository.findByIdAndActiveTrue(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		
		client.deactivate();
		repository.save(client);
	}
	
	private ClientResponseDTO toResponseDTO(Client client) {
		return new ClientResponseDTO(client.getId(),
									 client.getName(),
									 client.getCpf(),
									 client.getPhone());
	}
}
