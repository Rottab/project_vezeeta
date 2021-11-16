package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.controllers.models.requests.ScheduleCreateRequest;
import com.sumerge.vezeeta.models.Clinic;
import com.sumerge.vezeeta.models.Doctor;
import com.sumerge.vezeeta.models.Schedule;
import com.sumerge.vezeeta.services.ClinicService;
import com.sumerge.vezeeta.services.DoctorService;
import com.sumerge.vezeeta.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/schedules")
public class SchedulesController {

    @Autowired
    private ScheduleService scheduleService;


    @GetMapping("/doctors/{id}")
    public ResponseEntity<List<Schedule>> getFullSchedule(@PathVariable Integer id) {
        List<Schedule> fullDoctorSchedule = scheduleService.getFullDoctorSchedule(id);
        return ResponseEntity.ok(fullDoctorSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Integer id) {
        Schedule schedule = scheduleService.getSchedule(id);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping
    public ResponseEntity<Schedule> addDoctorSchedule(@Valid @RequestBody ScheduleCreateRequest request) {
        Schedule newSchedule = scheduleService.addDoctorDaySchedule(request.getDayOfWeek(),
                request.getDoctorId(),
                request.getClinicId(),
                request.getTimeFrom(),
                request.getTimeTo());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newSchedule.getId())
                .toUri();
        return ResponseEntity.created(location).body(newSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Integer id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

}
