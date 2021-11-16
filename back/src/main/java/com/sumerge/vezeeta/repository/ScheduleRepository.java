package com.sumerge.vezeeta.repository;

import com.sumerge.vezeeta.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Optional<Schedule> findById(Integer id);

    List<Schedule> findAllByDoctorId(Integer id);

    Schedule findScheduleByDayOfWeekAndDoctorId(DayOfWeek dayOfWeek, Integer id);
}
