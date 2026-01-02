package com.LGR.video_store.dtos;

import jakarta.validation.constraints.Size;

public class EmployeePatchDTO {

	@Size(min = 3, message = "Name must have 3 characters")
	private String name;
	
	@Size(min = 11, message = "CPF must have 11 characters")
	private String cpf;

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}
}
