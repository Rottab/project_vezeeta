package com.sumerge.vezeeta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "clinic_id", "dayOfWeek"}))
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonBackReference
    private Doctor doctor;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonManagedReference
    private Clinic clinic;
    @NotBlank
    private DayOfWeek dayOfWeek;
    @NotBlank
    private Time timeFrom;
    @NotBlank
    private Time timeTo;

    public Schedule() {
    }

    public Schedule(Doctor doctor, Clinic clinic, DayOfWeek dayOfWeek, Time timeFrom, Time timeTo) {
        this.doctor = doctor;
        this.clinic = clinic;
        this.dayOfWeek = dayOfWeek;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(Time from) {
        this.timeFrom = from;
    }

    public Time getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Time to) {
        this.timeTo = to;
    }
}
