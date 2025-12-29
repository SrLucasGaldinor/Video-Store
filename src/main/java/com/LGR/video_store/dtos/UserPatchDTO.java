package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.Role;

public class UserPatchDTO {

	private String userName;
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
