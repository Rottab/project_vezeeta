package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.models.Clinic;
import com.sumerge.vezeeta.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    @Transactional
    public Clinic getClinic(Integer id) {
        return clinicRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Clinic.class, id));
    }

    @Transactional
    public List<Clinic> getClinics() {
        return clinicRepository.findAll();
    }

    @Transactional
    public void updateClinic(Integer id, String name, String address) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Clinic.class, id));
        clinic.setName(name);
        clinic.setAddress(address);
        clinicRepository.save(clinic);
    }
}
