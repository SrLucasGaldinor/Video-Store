package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
	
	@NotBlank
    private String userName;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotNull
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

