package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Appointment;
import com.example.hmspfa.services.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return null;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {

    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return null;
    }
}
