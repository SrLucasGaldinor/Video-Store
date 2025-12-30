package com.LGR.video_store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeCreateDTO {

	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "CPF is required")
	@Size(min = 11, message = "CPF must have 11 characteres")
	private String cpf;
	
	@NotNull(message = "User_id is required")
	private Long userId;

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
