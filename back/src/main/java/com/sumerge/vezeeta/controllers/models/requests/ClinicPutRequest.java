package com.sumerge.vezeeta.controllers.models.requests;

import javax.validation.constraints.NotBlank;

public class ClinicPutRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;

    public ClinicPutRequest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public ClinicPutRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
