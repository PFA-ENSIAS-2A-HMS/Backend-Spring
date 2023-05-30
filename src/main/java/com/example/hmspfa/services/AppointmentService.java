package com.example.hmspfa.services;

import com.example.hmspfa.entities.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    Appointment updateAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();
    // TODO : add other methods
}
