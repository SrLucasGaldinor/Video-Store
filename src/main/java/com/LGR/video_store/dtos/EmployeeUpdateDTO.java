package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeUpdateDTO {

	@NotBlank(message = "Name can't be blank")
	private String name;
	
	@NotBlank(message = "CPF can't be blank")
	@Size(min = 11, message = "CPF must have 11 characteres")
	private String cpf;

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}
}
