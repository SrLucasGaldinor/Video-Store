package com.LGR.video_store.dtos;

import jakarta.validation.constraints.Size;

public class ClientPatchDTO {

	@Size(min = 3, message = "must have 3 characters")
	private String name;
	
	@Size(min = 3, message = "must have 11 characters")
	private String cpf;
	
	@Size(min = 3, message = "must have 11 characters")
	private String phone;

	public ClientPatchDTO(String name, String cpf, String phone) {
		this.name = name;
		this.cpf = cpf;
		this.phone = phone;
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
