package com.sumerge.vezeeta.controllers.models.responses;

import com.sumerge.vezeeta.models.Appointment;
import com.sumerge.vezeeta.models.Schedule;

import java.util.List;

public class DoctorAvailabilityResponse {

    private List<Schedule> scheduleList;
    private List<Appointment> appointmentList;

    public DoctorAvailabilityResponse(List<Schedule> scheduleList, List<Appointment> appointmentList) {
        this.scheduleList = scheduleList;
        this.appointmentList = appointmentList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
