package com.organizai.app.model.login;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String username;
    private String password;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
