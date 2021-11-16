package com.sumerge.vezeeta.controllers.models.requests;


import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class AppointmentCreateRequest {

    @NotBlank
    @Future
    private LocalDateTime date;
    @NotBlank
    private Integer clinicId;
    @NotBlank
    private Integer doctorId;

    public AppointmentCreateRequest(LocalDateTime date) {
        this.date = date;
    }

    public AppointmentCreateRequest() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
