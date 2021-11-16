package com.sumerge.vezeeta.controllers.models.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AppUserPatchRequest {
    @NotBlank
    @Size(max = 80)
    private String password;

    public AppUserPatchRequest(String password) {
        this.password = password;
    }

    public AppUserPatchRequest() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
