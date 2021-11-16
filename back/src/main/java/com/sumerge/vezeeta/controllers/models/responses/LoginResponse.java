package com.sumerge.vezeeta.controllers.models.responses;

import java.util.List;

public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private Integer expiresIn;
    private List<String> roles;

    public LoginResponse(String token, String username, Integer expiresIn, List<String> roles) {
        this.token = token;
        this.username = username;
        this.expiresIn = expiresIn;
        this.roles = roles;
    }


    public String getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", username='" + username + '\'' +
                ", expiresIn=" + expiresIn +
                ", roles=" + roles +
                '}';
    }
}
