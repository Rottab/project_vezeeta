package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.controllers.models.requests.ClinicPutRequest;
import com.sumerge.vezeeta.models.Clinic;
import com.sumerge.vezeeta.services.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/clinics")
public class ClinicsController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public ResponseEntity<List<Clinic>> getClinics() {
        return ResponseEntity.ok(clinicService.getClinics());
    }


    // Getting a 500 error when I try to serialize the clinic object
    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getClinic(@PathVariable Integer id) {
        Clinic clinic = clinicService.getClinic(id);
        return ResponseEntity.ok(clinic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClinic(@PathVariable Integer id, @Valid @RequestBody ClinicPutRequest request) {
        clinicService.updateClinic(id, request.getName(), request.getAddress());
        return ResponseEntity.ok(null);
    }
}
