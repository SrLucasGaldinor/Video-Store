package com.LGR.video_store.dtos;

public class ClientResponseDTO {

	private Long id;
	private String name;
	private String cpf;
	private String phone;
	
	public ClientResponseDTO(Long id, String name, String cpf, String phone) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}
}
