package com.sumerge.vezeeta.controllers.models.requests;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentPatchRequest {

    @NotBlank
    private Integer id;

    @NotBlank
    @Future
    private LocalDateTime date;

    public AppointmentPatchRequest(Integer id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public AppointmentPatchRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
