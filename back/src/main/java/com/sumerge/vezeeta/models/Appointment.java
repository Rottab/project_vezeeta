package com.sumerge.vezeeta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime date;

    @ManyToOne
    @JsonManagedReference
    private Doctor doctor;
    @ManyToOne
    @JsonManagedReference
    private AppUser user;
    @ManyToOne
    @JsonManagedReference
    private Clinic clinic;

    private Boolean approved;

    public Appointment() {
    }

    public Appointment(Integer id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public Appointment(LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public Appointment(LocalDateTime date, Doctor doctor, AppUser user, Clinic clinic) {
        this.date = date;
        this.doctor = doctor;
        this.user = user;
        this.clinic = clinic;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", doctor=" + doctor +
                ", user=" + user +
                ", clinic=" + clinic +
                ", approved=" + approved +
                '}';
    }
}
