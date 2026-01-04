package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientCreateDTO {

	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "CPF is required")
	@Size(min = 11, message = "CPF must have 11 characters")
	private String cpf;
	
	
	@Size(min = 11, message = "Phone must have 11 characters")
	private String phone;


	public ClientCreateDTO(String name, String cpf, String phone) {
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
