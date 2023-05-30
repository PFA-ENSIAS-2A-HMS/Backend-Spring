package com.example.hmspfa.services;

import com.example.hmspfa.entities.Appointment;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    Appointment updateAppointment(Appointment appointment);
    // TODO : add other methods
}
