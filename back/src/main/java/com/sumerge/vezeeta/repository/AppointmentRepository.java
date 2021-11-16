package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findById(Integer id);

    List<Appointment> findAllByUserEquals(AppUser user);

    List<Appointment> findAllByDoctorId(Integer id);

    @Query("SELECT a FROM Appointment a WHERE a.approved = true AND a.date > current_date AND a.doctor.id = :doctorId")
    List<Appointment> findUpcomingAppointments(@Param("doctorId") Integer doctorId);

    void deleteById(Integer id);
}
