package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.controllers.models.requests.AppointmentCreateRequest;
import com.sumerge.vezeeta.controllers.models.requests.AppointmentPatchRequest;
import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.models.Appointment;
import com.sumerge.vezeeta.models.Clinic;
import com.sumerge.vezeeta.models.Doctor;
import com.sumerge.vezeeta.security.models.AppUserDetails;
import com.sumerge.vezeeta.services.AppUserService;
import com.sumerge.vezeeta.services.AppointmentService;
import com.sumerge.vezeeta.services.ClinicService;
import com.sumerge.vezeeta.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentsController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private AppUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable Integer id) {
        Appointment appointment = appointmentService.getAppointment(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<?> getMyAppointments(@AuthenticationPrincipal AppUserDetails userDetails) {
        boolean isDoctor = userDetails.getAuthorities().stream().anyMatch(grantedAuthority -> {
            if (grantedAuthority.getAuthority() == "ROLE_DOCTOR") return true;
            return false;
        });
        List<Appointment> appointments = null;
        AppUser appUser = userService.getUser(userDetails.getId());
        if (isDoctor) appointments = appointmentService.getDoctorAppointments(appUser);
        else appointments = appointmentService.getAppointments(appUser);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@Valid @RequestBody AppointmentCreateRequest request, @AuthenticationPrincipal AppUserDetails userDetails) {
        AppUser appUser = userService.getUser(userDetails.getId());
        Doctor doctor = doctorService.getDoctor(request.getDoctorId());
        Clinic clinic = clinicService.getClinic(request.getClinicId());
        Appointment newAppointment = new Appointment(request.getDate(), doctor, appUser, clinic);
        Appointment appointment = appointmentService.create(newAppointment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(appointment.getId())
                .toUri();
        return ResponseEntity.created(location).body(appointment);
    }

    @PatchMapping
    public ResponseEntity<?> updateAppointment(@Valid @RequestBody AppointmentPatchRequest request) {
        Appointment appointment = appointmentService.updateDate(request.getId(), request.getDate());
        return ResponseEntity.ok(appointment);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> approveAppointment(@PathVariable Integer id) {
        appointmentService.approveAppointment(id);
        // I actually need to validate whether this user can approve the appointment
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
