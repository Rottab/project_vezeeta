package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.controllers.models.requests.DoctorCreateRequest;
import com.sumerge.vezeeta.controllers.models.responses.DoctorAvailabilityResponse;
import com.sumerge.vezeeta.models.*;
import com.sumerge.vezeeta.security.models.AppUserDetails;
import com.sumerge.vezeeta.services.AppUserService;
import com.sumerge.vezeeta.services.AppointmentService;
import com.sumerge.vezeeta.services.DoctorService;
import com.sumerge.vezeeta.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorsController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppUserService userService;


    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody DoctorCreateRequest request) {
        AppUser user = userService.getUser(request.getUserId());
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        Doctor newDoctor = doctorService.addDoctor(doctor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDoctor.getId())
                .toUri();
        return ResponseEntity.created(location).body(newDoctor);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors() {
        List<Doctor> doctors = doctorService.getDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/self")
    public ResponseEntity<Doctor> getCurrentDoctor(@AuthenticationPrincipal AppUserDetails userDetails) {
        Doctor doctor = doctorService.getDoctorByUserId(userDetails.getId());
        return ResponseEntity.ok(doctor);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable Integer id) {
        Doctor doctor = doctorService.getDoctor(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<Doctor>> getDoctors(@PathVariable Integer id) {
        List<Doctor> doctors = doctorService.getDoctorsByCategory(id);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<DoctorAvailabilityResponse> getDoctorAvailability(@PathVariable Integer id) {
        List<Appointment> availability = appointmentService.getUpcomingAppointments(id);
        List<Schedule> schedule = scheduleService.getFullDoctorSchedule(id);
        DoctorAvailabilityResponse availabilityResponse = new DoctorAvailabilityResponse(schedule, availability);
        return ResponseEntity.ok(availabilityResponse);
    }
}
