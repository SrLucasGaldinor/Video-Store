package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.Role;

import jakarta.validation.constraints.Size;

public class UserPatchDTO {

	@Size(min = 3, message = "Username must have at least 3 characters")
	private String userName;
	
	@Size(min = 6, message = "Password must have at least 6 characters")
	private String password;
	private Role role;
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}
	
}
