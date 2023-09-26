package com.organizai.app.model;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
