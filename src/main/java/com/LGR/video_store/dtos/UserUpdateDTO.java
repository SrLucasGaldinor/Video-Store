package com.LGR.video_store.dtos;

import com.LGR.video_store.enums.Role;
import jakarta.validation.constraints.Size;

public class UserUpdateDTO {

    private String userName;

    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    private Role role;

    public UserUpdateDTO() {
    	
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

