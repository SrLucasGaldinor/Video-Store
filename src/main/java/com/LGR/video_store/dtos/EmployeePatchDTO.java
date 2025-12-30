package com.LGR.video_store.dtos;

import jakarta.validation.constraints.Size;

public class EmployeePatchDTO {

	private String name;
	
	@Size(min = 11, message = "CPF must have 11 characteres")
	private String cpf;

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}
}
