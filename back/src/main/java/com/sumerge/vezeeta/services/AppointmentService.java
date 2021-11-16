package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.models.Appointment;
import com.sumerge.vezeeta.models.Doctor;
import com.sumerge.vezeeta.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorService doctorService;

    @Transactional
    public List<Appointment> getAppointments(AppUser user) {
        return appointmentRepository.findAllByUserEquals(user);
    }

    public Appointment getAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(Appointment.class, id));
        return appointment;
    }

    @Transactional
    public Appointment create(Appointment appointment) {
        // Here should only be the validation
        return appointmentRepository.save(appointment);
    }

    public Appointment updateDate(Integer id, LocalDateTime date) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Appointment.class, id));
        appointment.setDate(date);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Appointment.class, id));
        appointmentRepository.delete(appointment);
    }

    public void approveAppointment(Integer id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Appointment.class, id));
        appointment.setApproved(true);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getUpcomingAppointments(Integer id) {
        return appointmentRepository.findUpcomingAppointments(id);
    }

    public List<Appointment> getDoctorAppointments(AppUser appUser) {
        Doctor doctor = doctorService.getDoctorByUserId(appUser.getId());
        return appointmentRepository.findAllByDoctorId(doctor.getId());
    }
}
