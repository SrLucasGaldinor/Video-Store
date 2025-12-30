package com.LGR.video_store.dtos;

public class EmployeeResponseDTO {

	private Long id;
	private String name;
	private String cpf;
	private Long userId;
	
	public EmployeeResponseDTO(Long id, String name, String cpf, Long userId) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Long getUserId() {
		return userId;
	}
}
