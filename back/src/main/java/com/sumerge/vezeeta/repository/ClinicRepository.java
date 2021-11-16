package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Integer> {
    Optional<Clinic> findById(Integer id);
}
