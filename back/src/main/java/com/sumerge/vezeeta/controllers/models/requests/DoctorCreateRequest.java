package com.sumerge.vezeeta.controllers.models.requests;


import javax.validation.constraints.NotBlank;

public class DoctorCreateRequest {

    @NotBlank
    private Integer userId;

    public DoctorCreateRequest(Integer userId) {
        this.userId = userId;
    }

    public DoctorCreateRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
