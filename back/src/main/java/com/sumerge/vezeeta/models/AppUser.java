package com.sumerge.vezeeta.models;

import com.fasterxml.jackson.annotation.*;
import com.sumerge.vezeeta.utils.ConstrainHandler;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = ConstrainHandler.USERNAME_USED, columnNames = "username"),
        @UniqueConstraint(name = ConstrainHandler.EMAIL_USED, columnNames = "email")
})
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotBlank
    @Size(min = 4, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 4, max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;
    @NotBlank
    @Size(min = 4, max = 80)
    @JsonIgnore
    private String password;
    @ManyToMany
    @JoinTable
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Appointment> appointments = new LinkedList<>();


    public AppUser() {
    }

    public AppUser(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
