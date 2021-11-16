package com.sumerge.vezeeta.controllers.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sumerge.vezeeta.models.Doctor;
import com.sumerge.vezeeta.utils.SqlTimeDeserializer;
import jdk.jfr.Timespan;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.DayOfWeek;

public class ScheduleCreateRequest {

    @NotBlank
    private DayOfWeek dayOfWeek;
    @NotBlank
    private Integer doctorId;
    @NotBlank
    private Integer clinicId;
    @NotBlank
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    private Time timeFrom;
    @NotBlank
    @JsonFormat(pattern = "HH:mm:ss")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    private Time timeTo;

    public ScheduleCreateRequest() {
    }

    public ScheduleCreateRequest(DayOfWeek dayOfWeek, Integer doctorId, Integer clinicId, Time timeFrom, Time timeTo) {
        this.dayOfWeek = dayOfWeek;
        this.doctorId = doctorId;
        this.clinicId = clinicId;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }


    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time timeTo) {
        this.timeTo = timeTo;
    }
}
