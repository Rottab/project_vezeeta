package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.Appointment;
import com.sumerge.vezeeta.models.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void findUpcomingAppointments() {
        // Given
        Doctor doctor = new Doctor();
        doctorRepository.save(doctor);
        Appointment appointment1 = new Appointment(LocalDateTime.parse("2015-08-04T10:11:30"));
        Appointment appointment2 = new Appointment(LocalDateTime.parse("2024-01-04T10:11:30"));
        Appointment appointment3 = new Appointment(LocalDateTime.parse("2024-01-04T10:11:30"));
        appointment1.setApproved(true);
        appointment2.setApproved(true);
        appointment3.setApproved(false);
        appointment1.setDoctor(doctor);
        appointment2.setDoctor(doctor);
        appointment3.setDoctor(doctor);
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        // When
        List<Appointment> upcomingAppointments = appointmentRepository.findUpcomingAppointments(doctor.getId());
        // Then
        assertThat(upcomingAppointments.size()).isEqualTo(1);
    }
}
