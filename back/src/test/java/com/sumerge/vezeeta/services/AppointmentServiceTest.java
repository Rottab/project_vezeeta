package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.models.Appointment;
import com.sumerge.vezeeta.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;
    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAppointment() {
        // Given
        Integer id = 1;
        Appointment appointment = new Appointment();
        when(appointmentRepository.findById(id)).thenReturn(java.util.Optional.of(appointment));
        // When
        Appointment result = appointmentService.getAppointment(id);
        // Then
        assertThat(result).isEqualTo(appointment);
        // why though?
        verify(appointmentRepository).findById(id);

    }

    @Test
    void getAppointmentThrow() {
        // Given
        Integer id = 1;
        when(appointmentRepository.findById(id)).thenThrow(new DataNotFoundException(Appointment.class, id));
        // When
        // Then
        assertThatThrownBy(()-> appointmentService.getAppointment(id)).isInstanceOf(DataNotFoundException.class);

    }
}
