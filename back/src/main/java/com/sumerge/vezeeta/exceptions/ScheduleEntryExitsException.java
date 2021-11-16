package com.sumerge.vezeeta.exceptions;

import java.time.DayOfWeek;

public class ScheduleEntryExitsException extends RuntimeException {
    public ScheduleEntryExitsException(DayOfWeek dayOfWeek, Integer doctorId, Integer clinicId) {
        super(String.format("Schedule Entry of day %s for doctor %d and clinic %d already exists", dayOfWeek, doctorId, clinicId));
    }
}
