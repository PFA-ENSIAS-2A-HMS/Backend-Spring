package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Appointment;
import com.example.hmspfa.exceptions.AppointmentNotFoundException;
import com.example.hmspfa.repositories.AppointmentRepository;
import com.example.hmspfa.services.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) throws AppointmentNotFoundException{
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                ()-> new AppointmentNotFoundException("Appointment Not Found")
        );
       return appointment;
    }

    @Override
    public void deleteAppointment(Long id) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                ()-> new AppointmentNotFoundException("Appointment Not Found")
        );
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
