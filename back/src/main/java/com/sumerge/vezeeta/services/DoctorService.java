package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.models.Doctor;
import com.sumerge.vezeeta.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private CategoryService categoryService;

    @Transactional
    public Doctor getDoctor(Integer id) {
        return doctorRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Doctor.class, id));

    }

    @Transactional
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Transactional
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional
    public List<Doctor> getDoctorsByCategory(Integer id) {
        // Just so it would throw an exception before trying to fetch all with invalid id
        categoryService.getCategory(id);
        List<Doctor> doctors = doctorRepository.findAllByCategory(id);
        return doctors;
    }

    public Doctor getDoctorByUserId(Integer id) {
        return doctorRepository.findDoctorByUserId(id).orElseThrow(() -> new DataNotFoundException(Doctor.class, id));
    }
}
