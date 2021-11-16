package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findById(Integer id);

    @Query("SELECT d from Doctor d JOIN d.category c WHERE c.id = :categoryId")
    List<Doctor> findAllByCategory(@Param("categoryId") Integer categoryId);

    @Query("SELECT d from Doctor d WHERE d.user.id = :userId")
    Optional<Doctor> findDoctorByUserId(@Param("userId") Integer userId);
}
