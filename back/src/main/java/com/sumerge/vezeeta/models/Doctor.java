package com.sumerge.vezeeta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JoinColumn
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private AppUser user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToOne
    @JsonManagedReference
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "doctor_id")
    @JsonManagedReference
    private Set<Schedule> schedule = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", user=" + user +
                ", appointments=" + appointments +
                ", category=" + category +
                ", schedule=" + schedule +
                '}';
    }
}
