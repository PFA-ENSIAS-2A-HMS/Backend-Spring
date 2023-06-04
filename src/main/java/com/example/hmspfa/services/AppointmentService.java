package com.example.hmspfa.services;

import com.example.hmspfa.entities.Appointment;
import com.example.hmspfa.entities.Doctor;
import com.example.hmspfa.exceptions.AppointmentNotFoundException;

import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    Appointment getAppointmentById(Long id) throws AppointmentNotFoundException;
    void deleteAppointment(Long id) throws AppointmentNotFoundException;
    Appointment updateAppointment(Appointment appointment) throws AppointmentNotFoundException;

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentByDoctor(Doctor doctor);
}
