package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {
	
	@NotBlank(message = "Username can't be blank")
    private String userName;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotNull(message = "Role can't be null")
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

