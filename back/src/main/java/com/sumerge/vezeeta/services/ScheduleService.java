package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.exceptions.ScheduleEntryExitsException;
import com.sumerge.vezeeta.models.Clinic;
import com.sumerge.vezeeta.models.Doctor;
import com.sumerge.vezeeta.models.Schedule;
import com.sumerge.vezeeta.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ClinicService clinicService;

    @Transactional
    public List<Schedule> getFullDoctorSchedule(Integer id) {
        return scheduleRepository.findAllByDoctorId(id);
    }

    @Transactional
    public Schedule getSchedule(Integer id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Schedule.class, id));
    }

    @Transactional
    public Schedule getDoctorSchedule(Integer id, DayOfWeek dayOfWeek) {
        return scheduleRepository.findScheduleByDayOfWeekAndDoctorId(dayOfWeek, id);
    }

    public Schedule addDoctorDaySchedule(DayOfWeek dayOfWeek, Integer doctorId, Integer clinicId, Time timeFrom, Time timeTo) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        Clinic clinic = clinicService.getClinic(clinicId);
        Schedule schedule = new Schedule(doctor, clinic, dayOfWeek, timeFrom, timeTo);
        try {
            Schedule newSchedule = scheduleRepository.save(schedule);
            return newSchedule;
        } catch (DataIntegrityViolationException e) {
            throw new ScheduleEntryExitsException(dayOfWeek, doctorId, clinicId);
        }
    }

    public void deleteSchedule(Integer id) {
        // I need to check if there is an upcoming appointment using this schedule first
        // If so the appointment has to be rejected/canceled
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Schedule.class, id));
        scheduleRepository.delete(schedule);
    }
}
